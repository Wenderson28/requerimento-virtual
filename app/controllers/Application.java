package controllers;

import play.*;

import play.mvc.*;

import java.util.*;
import interceptores.Seguranca;
import models.*;

@With(Seguranca.class)
public class Application extends Controller {

	
    public static void index() {
        render();
    }
    public static void login() {
    	render();
    }
    public static void autenticar() {
    	render();
    }
 

    

}