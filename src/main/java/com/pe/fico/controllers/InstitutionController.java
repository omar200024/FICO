package com.pe.fico.controllers;

import java.io.IOException;
import java.net.MalformedURLException;
import java.text.ParseException;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.pe.fico.entities.Institution;
import com.pe.fico.service.IInstitutionService;
import com.pe.fico.service.IUploadFileService;

@Controller
@RequestMapping("/institutions")
public class InstitutionController {

	@Autowired
	private IInstitutionService iService;

	@Autowired
	private IUploadFileService uploadService;

	@GetMapping("/new")
	public String newInstitution(Model model) {
		model.addAttribute("institution", new Institution());
		return "institution/institution";

	}

	@GetMapping("/list")
	public String listInstitutions(Model model) {
		try {
			model.addAttribute("institution", new Institution());
			model.addAttribute("listaInstituciones", iService.list());
		} catch (Exception e) {
			model.addAttribute("error", e.getMessage());
		}
		return "institution/listInstitutions";
	}

	@RequestMapping("/save")
	public String insertInstitution(@ModelAttribute @Valid Institution objInstitution, BindingResult binRes,
			Model model, @RequestParam("file") MultipartFile foto, RedirectAttributes flash, SessionStatus status)
			throws ParseException {
		if (binRes.hasErrors()) {
			return "institution/institution";
		} else {
			if (!foto.isEmpty()) {

				if (objInstitution.getIdInstitution() > 0 && objInstitution.getPhotoInstitution() != null
						&& objInstitution.getPhotoInstitution().length() > 0) {

					uploadService.delete(objInstitution.getPhotoInstitution());
				}

				String uniqueFilename = null;
				try {
					uniqueFilename = uploadService.copy(foto);
				} catch (IOException e) {
					e.printStackTrace();
				}

				flash.addFlashAttribute("info", "Has subido correctamente '" + uniqueFilename + "'");
				objInstitution.setPhotoInstitution(uniqueFilename);
			}
			boolean flag = iService.insert(objInstitution);
			if (flag) {
				return "redirect:/institutions/list";
			} else {
				model.addAttribute("mensaje", "Ocurrió un error");
				return "redirect:/institutions/new";
			}
		}
	}

	@GetMapping(value = "/uploads/{filename:.+}")
	public ResponseEntity<Resource> verFoto(@PathVariable String filename) {

		Resource recurso = null;

		try {
			recurso = uploadService.load(filename);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ResponseEntity.ok()
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + recurso.getFilename() + "\"")
				.body(recurso);
	}

	@GetMapping(value = "/view/{id}")
	public String view(@PathVariable(value = "id") int id, Map<String, Object> model, RedirectAttributes flash) {

		Institution institution = iService.listarId(id);

		if (institution == null) {
			flash.addFlashAttribute("error", "La instituion no existe en la base de datos");
			return "institution/listInstitutions";
		}

		model.put("institution", institution);
		model.put("titulo", "Detalle de institucion: " + institution.getNameInstitution());

		return "institution/ver";
	}

	@RequestMapping("/list")
	public String listIsntitutions(Map<String, Object> model) {
		model.put("listaInstituciones", iService.list());
		return "institution/listInstitutions";

	}

	@RequestMapping("/listarId")
	public String listarId(Map<String, Object> model, @ModelAttribute Institution inst) {
		iService.listarId(inst.getIdInstitution());
		return "institution/listInstitutions";

	}

	@RequestMapping("/update/{id}")
	public String update(@PathVariable int id, Model model, RedirectAttributes objRedir) {

		Institution objIns = iService.listarId(id);
		if (objIns == null) {
			objRedir.addFlashAttribute("mensaje", "OcurriÃ³ un error");
			return "redirect:/institutions/list";
		} else {
			model.addAttribute("institution", objIns);
			return "institution/institution";
		}
	}

}
