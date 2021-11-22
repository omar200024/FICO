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

import com.pe.fico.entities.Product;
import com.pe.fico.entities.SavingAccountProduct;
import com.pe.fico.service.IProductService;
import com.pe.fico.service.ISavingAccountService;
import com.pe.fico.service.ITypeSavingAccountService;

@Controller
@RequestMapping("/savingaccounts")
public class SavingAccountProductController {
	@Autowired
	private ISavingAccountService sap;
	
	@Autowired
	private ITypeSavingAccountService tap;
	
	@Autowired
	private IProductService pService;
	
	@GetMapping("/new")
	public String newAccount(Model model) {
		model.addAttribute("savingaccount", new SavingAccountProduct());
		model.addAttribute("listaTipoCuentasAhorro", tap.list());
		model.addAttribute("listaProductos", byType());
		model.addAttribute("savingaccount", new SavingAccountProduct());
		return "savingaccount/savingaccount";
	}
	
	@GetMapping("/list")
	public String listAccounts(Model model) {
		try {
			model.addAttribute("savingaccount", new SavingAccountProduct());
			model.addAttribute("listaCuentasAhorro", sap.list());
		} catch (Exception e) {
			model.addAttribute("error", e.getMessage());
		}
		return "savingaccount/listSavingAccounts";
	}
	
	@RequestMapping("/save")
	public String insertAccounts(@ModelAttribute @Valid SavingAccountProduct objSap, BindingResult binRes, Model model)
			throws ParseException {
		if (binRes.hasErrors()) {
			model.addAttribute("listaTipoCuentasAhorro", tap.list());
			model.addAttribute("listaProductos", byType());
			return "savingaccount/savingaccount";
		} else {
			boolean flag = sap.insert(objSap);
			if (flag) {
				return "redirect:/savingaccounts/list";
			} else {
				model.addAttribute("mensaje", "Ocurrió un error");
				return "redirect:/savingaccounts/new";
			}
		}

	}
	@RequestMapping("/list")
	public String listAccounts(Map<String, Object> model) {
		model.put("listaCuentasAhorro", sap.list());
		return "savingaccount/listSavingAccounts";

	}
	
	@RequestMapping("/listarId")
	public String listarId(Map<String, Object> model, @ModelAttribute SavingAccountProduct Sap) {
		sap.listarId(Sap.getIdSavingAccount());
		return "savingaccount/listSavingAccounts";

	}

	@RequestMapping("/update/{id}")
	public String update(@PathVariable int id, Model model, RedirectAttributes objRedir) {

		SavingAccountProduct objSap = sap.listarId(id);
		if (objSap == null) {
			objRedir.addFlashAttribute("mensaje", "OcurriÃ³ un error");
			return "redirect:/savingaccounts/list";
		} else {
			model.addAttribute("listaTipoCuentasAhorro", tap.list());
			model.addAttribute("listaProductos", byType());
			model.addAttribute("savingaccount", objSap);
			return "savingaccount/savingaccount";
		}
	}
	
	@RequestMapping("/delete/{id}")
	public String delete(@PathVariable int id, Model model, RedirectAttributes objRedir) {

		SavingAccountProduct objPro = sap.listarId(id);
		if (objPro == null) {
			objRedir.addFlashAttribute("mensaje", "OcurriÃ³ un error");
			return "redirect:/savingaccounts/list";
		} else {
			sap.delSAbyId(id);
			return "redirect:/savingaccounts/list";
		}
	}
	
	public List<Product> byType() {
		List<Product> listProduct = new ArrayList<Product>();
		for (int i = 0; i < pService.list().size(); i++) {
			if (pService.list().get(i).getCategory().getNameCategoryProduct().equals("Cuentas de Ahorro")) {
				listProduct.add(pService.list().get(i));
			}
		}
		return listProduct;
	}
	
	@RequestMapping("/search")
	public String findByType(Map<String, Object> model, @ModelAttribute SavingAccountProduct afp)
			throws ParseException {

		List<SavingAccountProduct> listSA;
		afp.setFreeOperationSA(afp.getFreeOperationSA());
		listSA = sap.findByOp(afp.getFreeOperationSA());
		model.put("savingaccount", new SavingAccountProduct());

		if (listSA.isEmpty()) {
			model.put("mensaje", "No se encontró");
		}
		
		model.put("listaCuentasAhorro", listSA);
		return "savingaccount/listSavingAccounts";
	}
}
