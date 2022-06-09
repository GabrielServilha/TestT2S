package com.projeto.porto.models;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;


@Entity
public class Cliente implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
@Id
private long rg;
private String nome;
private String email;

@OneToMany( mappedBy="cliente", cascade=CascadeType.ALL, orphanRemoval=true)
private List<Conteiners> conteiners;


public String getNome() {
	return nome;
}
public void setNome(String nome) {
	this.nome = nome;
}
public String getEmail() {
	return email;
}
public void setEmail(String email) {
	this.email = email;
}

public long getRg() {
	return rg;
}
public void setRg(long rg) {
	this.rg = rg;
}
public List<Conteiners> getConteiners() {
	return conteiners;
}
public void setContainers(List<Conteiners> conteiners) {
	this.conteiners = conteiners;
}

}
