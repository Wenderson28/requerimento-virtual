package controllers;

import java.io.File;
import java.util.List;

import models.Professor;
import models.Seac;
import play.cache.Cache;
import play.data.validation.Valid;
import play.mvc.Controller;

public class Professores extends Controller{
	
	public static void inicio(){
		render();
	}
	public static void encReq() {
		Professor professor = (Professor) Cache.get("professor");
		Cache.clear();
		render(professor);
	}
	
	public static void SalvarEnc(@Valid Professor professor, File foto) {
		
		if(foto == null) {
			flash.error("Adicione o arquivo!");
			encReq();
		}
		foto.renameTo(new File("./encaminhados/" + foto.getName()));
		
		professor.requerimento_prof = foto.getName();
		professor.save();
		flash.success("Requerimento Anexado!");
		encReq();
	}
	
	public void listarEnc(String busca) {
		
		List<Professor> professores;
		if(busca == null) {
			professores = Professor.findAll();
		} else {
			professores = Professor.find("byNumero_proLike", "%"+busca+"%").fetch();
			
		}
		render(professores);
	}
	
	
	public static void devolverReq() {
		Professor professor = (Professor) Cache.get("professor");
		Cache.clear();
		render(professor);
	}
	
	public static void salvarDev(@Valid Professor professor, File foto) {
		
		if(foto == null) {
			flash.error("Adicione o arquivo!");
			devolverReq();
		}
		foto.renameTo(new File("./devolver/" + foto.getName()));
		
		professor.requerimento = foto.getName();
		professor.save();
		flash.success("Requerimento Anexado!");
		devolverReq();
	}
	
}
