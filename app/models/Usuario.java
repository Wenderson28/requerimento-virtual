package models;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.validation.Valid;

import enums.TipoUsuario;

import play.data.validation.MaxSize;
import play.data.validation.MinSize;
import play.data.validation.Required;
import play.db.jpa.Blob;
import play.db.jpa.Model;
import play.libs.Crypto;

@Entity
public class Usuario extends Model{
	
	public Blob PDF;
	
	
	@Required
	public String nome;
	
	@MinSize(6)
	@MaxSize(15)
	@Required
	public String matricula;
	
	@MinSize(6)
	@Required
	public String senha;

	
	public void setSenha(String s) {
		
		senha = Crypto.passwordHash(s);
	}
	
	
	
	@OneToMany
	@JoinColumn(name="NOME_id")
	public List<Seac> seacs;
	
	
	@Required
	public TipoUsuario tipoUser;
	public Usuario() {
		tipoUser = TipoUsuario.ALUNO;	
		tipoUser = TipoUsuario.PROFESSOR;
		tipoUser = TipoUsuario.SEAC;
		
	}
	
}