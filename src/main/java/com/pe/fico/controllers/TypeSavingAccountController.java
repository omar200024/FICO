package com.pe.fico.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.support.SessionStatus;

import com.pe.fico.entities.TypeSavingAccount;
import com.pe.fico.service.ITypeSavingAccountService;

@Controller
@RequestMapping("/typesavingaccounts")
public class TypeSavingAccountController {

	@Autowired
	private ITypeSavingAccountService tService;
	
	@GetMapping("/new")
	public String newCategory(Model model) {
		model.addAttribute("typesavingaccount", new TypeSavingAccount());
		return "typesavingaccount/typesavingaccount";
	}
	
	@GetMapping("/list")
	public String listTypeSavingAccounts(Model model) {
		try {
			model.addAttribute("typesavingaccount", new TypeSavingAccount());
			model.addAttribute("listaTipoCuentasAhorro", tService.list());
		} catch (Exception e) {
			model.addAttribute("error", e.getMessage());
		}
		return "typesavingaccount/listTypesavingaccounts";
	}
	
	@PostMapping("/save")
	public String saveMarca(@Valid TypeSavingAccount typesavingaccount, BindingResult result, Model model, SessionStatus status)
			throws Exception {
		if (result.hasErrors()) {
			return "typesavingaccount/typesavingaccount";
		} else {
			int rpta = tService.insert(typesavingaccount);
			if (rpta > 0) {
				model.addAttribute("mensaje", "Ya existe");
				return "typesavingaccount/typesavingaccount";
			} else {
				model.addAttribute("mensaje", "Se guardó correctamente");
				status.setComplete();
			}
		}
		model.addAttribute("typesavingaccount", new TypeSavingAccount());
		return "redirect:/typesavingaccounts/list";
	}
}
