package controllers;


import java.util.Arrays;
import java.util.List;

import java.io.File;

import enums.TipoUsuario;
import models.Professor;
import models.Seac;

import models.Usuario;
import play.cache.Cache;
import play.data.validation.Valid;
import play.mvc.Controller;

public class Seacs extends Controller{

	public static void inicio(){
		render();
	}
	
	
	public static void form() {
		render();
	}
	public static void cadastro() {
		render();
	}
	
	
	public static void listar() {
		List<Usuario> usuarios = Usuario.findAll();
		render(usuarios);
	}
	public static void editar(Long id) {
		Usuario usuario = Usuario.findById(id);
		List<TipoUsuario> tiposUser = Arrays.asList(TipoUsuario.PROFESSOR, TipoUsuario.ALUNO);
		renderTemplate("Seacs/cadEdit.html", usuario, tiposUser);
		
	}
	public static void cadEdit() {
		Usuario usuario = (Usuario) Cache.get("usuario");
		Cache.clear();	
		List<TipoUsuario> tiposUser = Arrays.asList(TipoUsuario.PROFESSOR, TipoUsuario.ALUNO);
		render(tiposUser, usuario);
	}
	public static void cadSalvar(Usuario usuario, String senha) {
		if(senha.equals("") == false) {
			usuario.senha = senha;			
		}
		validation.valid(usuario);
		
		if(validation.hasErrors()){
			validation.keep();
			
			flash.error("Cadastro Inválido!");
			Cache.set("usuario", usuario);
			
			cadEdit();
		}
			
		usuario.save();
		flash.success("Cadastro Concluido!");
		listar();
	}
	
		
	
	public void listarReq(String busca) {
		
		List<Seac> seacs;
		if(busca == null) {
			seacs = Seac.findAll();
		} else {
			seacs = Seac.find("byMatriculaLike", "%"+busca+"%").fetch();
			
		}
		render(seacs);
	}


	public static void remover(Long id) {
		Usuario usuario = Usuario.findById(id);
		usuario.delete();
		listar();
	}
	
	
	
	public void listarReq_Def(String busca) {
		
		List<Professor> professores;
		if(busca == null) {
			professores = Professor.findAll();
		} else {
			professores = Professor.find("byMatriculaLike", "%"+busca+"%").fetch();
			
		}
		render(professores);
	}
	
	
	
	
	
	
	public static void sugestao() {
		render();
	}
	public static void Contato() {
		render();
	}
		
}

