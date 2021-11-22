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
import com.pe.fico.service.ICategoryProductService;
import com.pe.fico.service.IInstitutionService;
import com.pe.fico.service.IProductService;

@Controller
@RequestMapping("/products")
public class ProductController {

	@Autowired
	private IProductService pService;

	@Autowired
	private ICategoryProductService cService;

	@Autowired
	private IInstitutionService sService;

	@GetMapping("/new")
	public String newProduct(Model model) {
		model.addAttribute("product", new Product());
		model.addAttribute("listaCategorias", cService.list());
		model.addAttribute("listaInstituciones", sService.list());
		model.addAttribute("product", new Product());
		return "product/product";
	}

	@GetMapping("/list")
	public String listProducts(Model model) {
		try {
			model.addAttribute("product", new Product());
			model.addAttribute("listaProductos", pService.list());
		} catch (Exception e) {
			model.addAttribute("error", e.getMessage());
		}
		return "product/listProducts";
	}

	@RequestMapping("/save")
	public String insertProduct(@ModelAttribute @Valid Product objPro, BindingResult binRes, Model model)
			throws ParseException {
		if (binRes.hasErrors()) {
			model.addAttribute("listaCategorias", cService.list());
			model.addAttribute("listaInstituciones", sService.list());
			return "product/product";
		} else {
			boolean flag = pService.insert(objPro);
			if (flag) {
				return "redirect:/products/list";
			} else {
				model.addAttribute("mensaje", "Ocurrió un error");
				return "redirect:/products/new";
			}
		}

	}

	@RequestMapping("/list")
	public String listPorducts(Map<String, Object> model) {
		model.put("listaProductos", pService.list());
		return "product/listProducts";

	}

	@RequestMapping("/listarId")
	public String listarId(Map<String, Object> model, @ModelAttribute Product pro) {
		pService.listarId(pro.getIdProduct());
		return "product/listProducts";

	}

	@RequestMapping("/update/{id}")
	public String update(@PathVariable int id, Model model, RedirectAttributes objRedir) {

		Product objPro = pService.listarId(id);
		if (objPro == null) {
			objRedir.addFlashAttribute("mensaje", "OcurriÃ³ un error");
			return "redirect:/products/list";
		} else {
			model.addAttribute("listaCategorias", cService.list());
			model.addAttribute("listaInstituciones", sService.list());
			model.addAttribute("product", objPro);
			return "product/product";
		}
	}

	@GetMapping("/listPopular")
    public String listProductsBest(Model model) {
        try {
            model.addAttribute("product", new Product());
            model.addAttribute("listaProductosPopulares", byRating());
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
        }
        return "product/listPopularProducts";
    }

    public List<Product> byRating() {
        List<Product> listProduct= new ArrayList<Product>();
        for (int i = 0; i < pService.list().size(); i++) {
            if (pService.list().get(i).getRatingProduct()>=3){
                listProduct.add(pService.list().get(i));
            }
        }
        return listProduct;
    }
}
