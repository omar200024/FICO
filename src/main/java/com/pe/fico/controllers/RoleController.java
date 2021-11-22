package com.pe.fico.controllers;

import java.util.Map;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.pe.fico.entities.Role;
import com.pe.fico.service.IRoleService;
import com.pe.fico.service.IUserService;

@Controller
@RequestMapping("roles")
public class RoleController {

	@Autowired
	private IUserService uS;
	
	@Autowired
	private IRoleService rS;

	@GetMapping("/new")
	public String newRole(Model model) {
		model.addAttribute("role", new Role());
		model.addAttribute("listaUsuarios", uS.list());
		return "role/role";
	}

	@PostMapping("/save")
	public String saveRole(@Valid Role role, BindingResult result, Model model, SessionStatus status) throws Exception {
		if (result.hasErrors()) {
			return "role/role";
		} else {

			rS.insert(role);
			model.addAttribute("mensaje", "Se guardó correctamente");
			status.setComplete();

		}
		model.addAttribute("listaRoles", rS.list());

		return "role/listRole";

	}

	@GetMapping("/list")
	public String listRole(Model model) {
		try {
			model.addAttribute("role", new Role());
			model.addAttribute("listaRoles", rS.list());
		} catch (Exception e) {
			model.addAttribute("error", e.getMessage());
		}
		return "role/listRole";
	}
	
	@RequestMapping("/delete")
	public String delete(@RequestParam(value = "id") Long id, Model model) {

		try {
			if (id != null && id > 0) {
				rS.delete(id);
			}
		} catch (Exception e) {
			System.out.println("Error al eliminar");
		}
		model.addAttribute("listaRoles", rS.list());
		return "role/listRole";

	}
	
	@RequestMapping("/update/{id}")
	public String update(@PathVariable long id, Model model, RedirectAttributes objRedir) {

		Optional<Role> role = rS.listarId(id);
		if (role == null) {
			objRedir.addFlashAttribute("mensaje", "Ocurrio un error");
			return "role/role";
		} else {
			model.addAttribute("role", role);
			return "role/role";
		}
	}
	
	@RequestMapping("/reportes")
	public String listReports(Model model) {
		
		return "reports/reports";
	}
		
	@RequestMapping("/reporte1")
	public String rolXedad(Map<String, Object> model) {

		model.put("listRolXedad", rS.rolXedad());
		return "reports/rolesXedad";
	}
}
