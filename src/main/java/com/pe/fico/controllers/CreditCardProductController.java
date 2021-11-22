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

import com.pe.fico.entities.CreditCardProduct;
import com.pe.fico.entities.Product;
import com.pe.fico.service.IBranchCreditCardService;
import com.pe.fico.service.ICreditCardProductService;
import com.pe.fico.service.IProductService;

@Controller
@RequestMapping("/creditCards")
public class CreditCardProductController {

	@Autowired
	private ICreditCardProductService cService;

	@Autowired
	private IBranchCreditCardService bService;

	@Autowired
	private IProductService pService;

	List<Product> listProduct;
	
	@GetMapping("/new")
	public String newCreditCard(Model model) {
		
		model.addAttribute("creditCard", new CreditCardProduct());
		model.addAttribute("listaMarcas", bService.list());
		model.addAttribute("listaProductos", byType());
		model.addAttribute("creditCard", new CreditCardProduct());
		return "creditcard/creditcard";
	}

	@GetMapping("/list")
	public String listCreditCards(Model model) {
		try {
			model.addAttribute("creditCard", new CreditCardProduct());
			model.addAttribute("listaTarjetas", cService.list());
		} catch (Exception e) {
			model.addAttribute("error", e.getMessage());
		}
		return "creditcard/listCards";
	}

	@RequestMapping("/save")
	public String insertCreditCard(@ModelAttribute @Valid CreditCardProduct objPro, BindingResult binRes, Model model)
			throws ParseException {
		if (binRes.hasErrors()) {
			model.addAttribute("listaMarcas", cService.list());
			model.addAttribute("listaProductos", byType());
			return "creditcard/creditcard";
		} else {
			boolean flag = cService.insert(objPro);
			if (flag) {
				return "redirect:/creditCards/list";
			} else {
				model.addAttribute("mensaje", "Ocurrió un error");
				return "redirect:/creditCards/new";
			}
		}

	}

	@RequestMapping("/list")
	public String listCerditCard(Map<String, Object> model) {
		model.put("listaTarjetas", cService.list());
		return "creditcard/listCards";

	}
	
	@RequestMapping("/listarId")
	public String listarId(Map<String, Object> model, @ModelAttribute CreditCardProduct pro) {
		cService.listarId(pro.getIdCreditCard());
		return "creditcard/listCards";

	}

	@RequestMapping("/update/{id}")
	public String update(@PathVariable int id, Model model, RedirectAttributes objRedir) {

		CreditCardProduct objPro = cService.listarId(id);
		if (objPro == null) {
			objRedir.addFlashAttribute("mensaje", "OcurriÃ³ un error");
			return "redirect:/creditCards/list";
		} else {
			model.addAttribute("listaMarcas", bService.list());
			model.addAttribute("listaProductos", byType());
			model.addAttribute("creditCard", objPro);
			return "creditcard/creditcard";
		}
	}
	
	@RequestMapping("/delete/{id}")
	public String delete(@PathVariable int id, Model model, RedirectAttributes objRedir) {

		CreditCardProduct objPro = cService.listarId(id);
		if (objPro == null) {
			objRedir.addFlashAttribute("mensaje", "OcurriÃ³ un error");
			return "redirect:/creditCards/list";
		} else {
			cService.delCredbyId(id);
			return "redirect:/creditCards/list";
		}
	}

	public List<Product> byType() {
		List<Product> listProduct= new ArrayList<Product>();
		for (int i = 0; i < pService.list().size(); i++) {
			if (pService.list().get(i).getCategory().getNameCategoryProduct().equals("Tarjeta de Credito")) {
				listProduct.add(pService.list().get(i));
			}
		}
		return listProduct;
	}

	@RequestMapping("/search")
	public String findByType(Map<String, Object> model, @ModelAttribute CreditCardProduct card) {

		List<CreditCardProduct> listCard;
		card.setBenefitCreditCard(card.getBenefitCreditCard());
		listCard = cService.findByBenefit(card.getBenefitCreditCard());
		model.put("creditCard", new CreditCardProduct());
		
		if (listCard.isEmpty()) {
			model.put("mensaje", "No se encontró");
		}
		model.put("listaTarjetas", listCard);
		return "creditcard/listCards";
	}
	
}
