package controllers;


import java.io.File;
import java.util.Date;
import java.util.List;

import models.Aluno;
import models.Professor;
import models.Seac;
import models.Usuario;
import play.cache.Cache;
import play.data.validation.Valid;
import play.mvc.Controller;

public class Alunos extends Controller{

	public static void inicio() {
		render();
	}
	public static void abrirReq() {
		Seac seac = (Seac) Cache.get("seac");
		Cache.clear();
		render(seac);
	}
	
	public static void salvarPDF(@Valid Seac seac, File foto) {
		
		if(foto == null) {
			flash.error("Adicione o arquivo!");
			abrirReq();
		}
		foto.renameTo(new File("./uploads/" + foto.getName()));
		
		
		if (validation.hasErrors()){
			validation.keep();
			
			flash.error("Cadastro Inválido!");
			Cache.set("seac", seac);				
			abrirReq();
			
		}
		
		seac.requerimento = foto.getName();
		seac.save();
		flash.success("Requerimento Anexado!");
		abrirReq();
	}




	public void sitReq(String busca) {
		
		List<Professor> professores;
		if(busca == null) {
			professores = Professor.findAll();
		} else {
			professores = Professor.find("byNumero", "%"+busca+"%").fetch();
			
		}
		render(professores);
		}

}
	/*
	public static void renderPDFAluno(Long idAluno) {
		Aluno aluno = Aluno.findById(4);
		response.setContentTypeIfNotSet(aluno.PDF.type());
		renderBinary(aluno.PDF.get());
	}


*/	



