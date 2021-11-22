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

import com.pe.fico.entities.LoanProduct;
import com.pe.fico.entities.Product;
import com.pe.fico.service.ILoanProductService;
import com.pe.fico.service.IProductService;

@Controller
@RequestMapping("/loans")
public class LoanProductController {

	@Autowired
	private ILoanProductService lService;

	@Autowired
	private IProductService pService;

	List<Product> listProduct;

	@GetMapping("/new")
	public String newLoan(Model model) {

		model.addAttribute("loan", new LoanProduct());
		model.addAttribute("listaProductos", byType());
		model.addAttribute("loan", new LoanProduct());
		return "loan/loan";
	}

	@GetMapping("/list")
	public String listCreditCards(Model model) {
		try {
			model.addAttribute("loan", new LoanProduct());
			model.addAttribute("listaPrestamos", lService.list());
		} catch (Exception e) {
			model.addAttribute("error", e.getMessage());
		}
		return "loan/listLoans";
	}

	@RequestMapping("/save")
	public String insertCreditCard(@ModelAttribute @Valid LoanProduct objPro, BindingResult binRes, Model model)
			throws ParseException {
		if (binRes.hasErrors()) {
			model.addAttribute("listaProductos", byType());
			return "loan/loan";
		} else {
			boolean flag = lService.insert(objPro);
			if (flag) {
				return "redirect:/loans/list";
			} else {
				model.addAttribute("mensaje", "Ocurrió un error");
				return "redirect:/loans/new";
			}
		}

	}

	@RequestMapping("/list")
	public String listCerditCard(Map<String, Object> model) {
		model.put("listaPrestamos", lService.list());
		return "loan/listLoans";

	}

	@RequestMapping("/listarId")
	public String listarId(Map<String, Object> model, @ModelAttribute LoanProduct pro) {
		lService.listarId(pro.getIdLoanPoduct());
		return "loan/listCards";

	}

	@RequestMapping("/update/{id}")
	public String update(@PathVariable int id, Model model, RedirectAttributes objRedir) {

		LoanProduct objPro = lService.listarId(id);
		if (objPro == null) {
			objRedir.addFlashAttribute("mensaje", "OcurriÃ³ un error");
			return "redirect:/loans/list";
		} else {
			model.addAttribute("listaProductos", byType());
			model.addAttribute("loan", objPro);
			return "loan/loan";
		}
	}

	@RequestMapping("/delete/{id}")
	public String delete(@PathVariable int id, Model model, RedirectAttributes objRedir) {

		LoanProduct objPro = lService.listarId(id);
		if (objPro == null) {
			objRedir.addFlashAttribute("mensaje", "OcurriÃ³ un error");
			return "redirect:/loans/list";
		} else {
			lService.delLoanbyId(id);
			return "redirect:/loans/list";
		}
	}
	public List<Product> byType() {
		List<Product> listProduct = new ArrayList<Product>();
		for (int i = 0; i < pService.list().size(); i++) {
			if (pService.list().get(i).getCategory().getNameCategoryProduct().equals("Prestamo Personal")) {
				listProduct.add(pService.list().get(i));
			}
		}
		return listProduct;
	}
	
	@RequestMapping("/search")
	public String findByType(Map<String, Object> model, @ModelAttribute LoanProduct loan) {

		List<LoanProduct> listLoan;
		loan.setCurrencyLoan(loan.getCurrencyLoan());
		listLoan = lService.findByCurrency(loan.getCurrencyLoan());
		model.put("loan", new LoanProduct());
		
		if (listLoan.isEmpty()) {
			model.put("mensaje", "No se encontró");
		}
		model.put("listaPrestamos", listLoan);
		return "loan/listLoans";
	}
}
