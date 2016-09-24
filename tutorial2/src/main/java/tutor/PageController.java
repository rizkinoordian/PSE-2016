package tutor;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class PageController
{
	@RequestMapping("/hello")
	public String index ()
	{
	return "hello";
	}

	@RequestMapping("/greeting")
	public String greeting (@RequestParam(value = "name", required = false, defaultValue = "dunia") String name, Model model)
	{
		model.addAttribute ("name", name);
		return "greeting";
	}

	@RequestMapping("/greeting/{name}")
	public String greetingPath (@PathVariable String name, Model model)
	{
		model.addAttribute("name", name);
		return "greeting";
	}

	@RequestMapping("/jumlah")
	public String jumlah (@RequestParam(value = "a", required = false, defaultValue = "0") Integer a,
							@RequestParam(value = "b", required = false, defaultValue = "0") Integer b, Model model)
	{
		model.addAttribute ("a", a);
		model.addAttribute ("b", b);
		return "jumlah";
	}


}