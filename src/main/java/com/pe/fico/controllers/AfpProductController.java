package com.pe.fico.controllers;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.pe.fico.entities.AfpProduct;
import com.pe.fico.entities.Product;
import com.pe.fico.service.IAfpProductService;
import com.pe.fico.service.IMethodAfpService;
import com.pe.fico.service.IProductService;

@Controller
@RequestMapping("/afps")
public class AfpProductController {


	@Autowired
	private IProductService pService;
	
	@Autowired
	private IMethodAfpService mService;
	
	@Autowired
	private IAfpProductService aService;

	@GetMapping("/new")
	public String newAfp(Model model) {
		
		model.addAttribute("afp", new AfpProduct());
		model.addAttribute("listaMetodos", mService.list());
		model.addAttribute("listaProductos", byType());
		model.addAttribute("afp", new AfpProduct());
		return "afp/afp";
	}

	@GetMapping("/list")
	public String listAfp(Model model) {
		try {
			model.addAttribute("afp", new AfpProduct());
			model.addAttribute("listaAfp", aService.list());
		} catch (Exception e) {
			model.addAttribute("error", e.getMessage());
		}
		return "afp/listAfps";
	}

	@RequestMapping("/save")
	public String insertAfp(@ModelAttribute @Valid AfpProduct objAfp, BindingResult binRes, Model model)
			throws ParseException {
		if (binRes.hasErrors()) {
			model.addAttribute("listaMetodos", mService.list());
			model.addAttribute("listaProductos", byType());
			return "afp/afp";
		} else {
			boolean flag =aService.insert(objAfp);
			if (flag) {
				return "redirect:/afps/list";
			} else {
				model.addAttribute("mensaje", "Ocurrió un error");
				return "redirect:/afps/new";
			}
		}

	}

	@RequestMapping("/list")
	public String listApf(Map<String, Object> model) {
		model.put("listaAfp", aService.list());
		return "afp/listAfps";

	}
	
	@RequestMapping("/listarId")
	public String listarId(Map<String, Object> model, @ModelAttribute AfpProduct pro) {
		aService.listarId(pro.getIdAfpProduct());
		return "creditcard/listAfps";

	}

	@RequestMapping("/update/{id}")
	public String update(@PathVariable int id, Model model, RedirectAttributes objRedir) {

		AfpProduct objPro = aService.listarId(id);
		if (objPro == null) {
			objRedir.addFlashAttribute("mensaje", "OcurriÃ³ un error");
			return "redirect:/afps/list";
		} else {
			model.addAttribute("listaMetodos", mService.list());
			model.addAttribute("listaProductos", byType());
			model.addAttribute("afp", objPro);
			return "afp/afp";
		}
	}
	@RequestMapping("/delete/{id}")
	public String delete(@PathVariable int id, Model model, RedirectAttributes objRedir) {

		AfpProduct objPro = aService.listarId(id);
		if (objPro == null) {
			objRedir.addFlashAttribute("mensaje", "OcurriÃ³ un error");
			return "redirect:/afps/list";
		} else {
			aService.delAfpbyId(id);
			return "redirect:/afps/list";
		}
	}
	public List<Product> byType() {
		List<Product> listProduct= new ArrayList<Product>();
		for (int i = 0; i < pService.list().size(); i++) {
			if (pService.list().get(i).getCategory().getNameCategoryProduct().equals("AFP")) {
				listProduct.add(pService.list().get(i));
			}
		}
		return listProduct;
	}
	
	@RequestMapping("/search")
	public String findByType(Map<String, Object> model, @ModelAttribute AfpProduct afp) {

		List<AfpProduct> listAfp;
		afp.setTypeAfp(afp.getTypeAfp());
		listAfp = aService.findByTypeAfp(afp.getTypeAfp());
		model.put("afp", new AfpProduct());
		
		if (listAfp.isEmpty()) {
			model.put("mensaje", "No se encontró");
		}
		model.put("listaAfp", listAfp);
		return "afp/listAfps";
	}
}
