package com.madnilo.pwdmeter.resource;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.madnilo.pwdmeter.model.Password;

@RestController
@RequestMapping("/passwords")
public class PasswordMeasureResource {
	
	@CrossOrigin
	@RequestMapping(method=RequestMethod.GET, path="/measure/{pwd}")
	public ResponseEntity<Password> measurePwdStrenght(@PathVariable("pwd") String pwd){
		Password pass = new Password();
		pass.setText(pwd);
		pass.setStrength("No Match");
		
		int add1 = this.numberOfCharacters(pwd);
		int add2 = this.uppercaseLetters(pwd);
		int add3 = this.lowercaseLetters(pwd);
		int add4 = this.numbers(pwd);

		pass.setStrength(Integer.toString(add1 + add2 + add3 + add4)+"%");
		return ResponseEntity.ok().body(pass);
	}
	
	
	private int numberOfCharacters(String pwd){
		int len = pwd.length();
		int n = len;
		return n*4;
	}
	
	private int uppercaseLetters(String pwd){
		int len = pwd.length();
		Pattern pattern = Pattern.compile("[A-Z]");
		Matcher matcher = pattern.matcher(pwd);
		int n = 0;
		while(matcher.find()){
			n++;
		}
		return (len-n)*2;
	}
	
	private int lowercaseLetters(String pwd){
		int len = pwd.length();
		Pattern pattern = Pattern.compile("[a-z]");
		Matcher matcher = pattern.matcher(pwd);
		int n = 0;
		while(matcher.find()){
			n++;
		}
		return (len-n)*2;
	}
	
	private int numbers(String pwd){
		Pattern pattern = Pattern.compile("[0-9]");
		Matcher matcher = pattern.matcher(pwd);
		int n = 0;
		while(matcher.find()){
			n++;
		}
		return n*4;
	}
}
