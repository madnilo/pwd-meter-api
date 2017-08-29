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
		pass.setStrength(0);
		
		int add1 = this.numberOfCharacters(pwd);
		int add2 = this.uppercaseLetters(pwd);
		int add3 = this.lowercaseLetters(pwd);
		int add4 = this.numbers(pwd);
		int add5 = this.symbols(pwd);
		int add6 = this.middleNumbersAndSymbols(pwd);
		
		int ded1 = this.lettersOnly(pwd);
		int ded2 = this.numbersOnly(pwd);
		int ded3 = this.repeatCharacters(pwd);
		int ded4 = this.consecutiveLowercaseLetters(pwd);
		int ded5 = this.consecutiveUppercaseLetters(pwd);
		int ded6 = this.consecutiveNumbers(pwd);
		int ded7 = this.sequentialLetters(pwd);
		int ded8 = this.sequentialNumbers(pwd);
		int ded9 = this.sequentialSymbols(pwd);

		pass.setStrength(add1 + add2 + add3 + add4 + add5 + add6
		- ded1 - ded2 - ded3 - ded4 - ded5 - ded6 - ded7 - ded8 - ded9);
		
		if(pass.getStrength() > 100) pass.setStrength(100);
		if(pass.getStrength() < 0) pass.setStrength(0);
		if(pwd.equals("vazio")) pass.setStrength(0);
		return ResponseEntity.ok().body(pass);
	}
	
	//additions//
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
	
	private int symbols(String pwd){
		Pattern pattern = Pattern.compile("[:?!@#$%^&*()]");
		Matcher matcher = pattern.matcher(pwd);
		int n = 0;
		while(matcher.find()){
			n++;
		}
		return n*6;
	}
	
	private int middleNumbersAndSymbols(String pwd){
		Pattern pattern = Pattern.compile("[:?!@#$%^&*()]|[0-9]");
		Matcher matcher = pattern.matcher(pwd);
		int n = 0;
		while(matcher.find()){
			n++;
		}
		return n*2;
	}
	
	//deductions//
	private int lettersOnly(String pwd){
		int len = pwd.length();
		Pattern pattern = Pattern.compile("[a-zA-Z]");
		Matcher matcher = pattern.matcher(pwd);
		int n = 0;
		while(matcher.find()){
			n++;
		}
		return len==n ? n : 0;
	}
	
	private int numbersOnly(String pwd){
		int len = pwd.length();
		Pattern pattern = Pattern.compile("[0-9]");
		Matcher matcher = pattern.matcher(pwd);
		int n = 0;
		while(matcher.find()){
			n++;
		}
		System.out.println(len==n);
		return len==n ? n*n : 0;
	}
	
	private int repeatCharacters(String pwd){
		Pattern pattern = Pattern.compile(".{2}");
		Matcher matcher = pattern.matcher(pwd);
		int n = 0;
		while(matcher.find()){
			n++;
		}
		return n;
	}
	
	private int consecutiveUppercaseLetters(String pwd){
		Pattern pattern = Pattern.compile("[A-Z][A-Z]");
		Matcher matcher = pattern.matcher(pwd);
		int n = 0;
		while(matcher.find()){
			n++;
		}
		return n*2;
	}
	
	private int consecutiveLowercaseLetters(String pwd){
		Pattern pattern = Pattern.compile("[a-z][a-z]");
		Matcher matcher = pattern.matcher(pwd);
		int n = 0;
		while(matcher.find()){
			n++;
		}
		return n*2;
	}
	
	private int consecutiveNumbers(String pwd){
		Pattern pattern = Pattern.compile("[0-9]{2}");
		Matcher matcher = pattern.matcher(pwd);
		int n = 0;
		while(matcher.find()){
			n++;
		}
		return n*2;
	}
	
	private int sequentialLetters(String pwd){
		Pattern pattern = Pattern.compile("[a-zA-Z]{3}");
		Matcher matcher = pattern.matcher(pwd);
		int n = 0;
		while(matcher.find()){
			n++;
		}
		return n*3;
	}
	
	private int sequentialNumbers(String pwd){
		Pattern pattern = Pattern.compile("[0-9]{3}");
		Matcher matcher = pattern.matcher(pwd);
		int n = 0;
		while(matcher.find()){
			n++;
		}
		return n*3;
	}
	
	private int sequentialSymbols(String pwd){
		Pattern pattern = Pattern.compile("[:?!@#$%^&*()]{3}");
		Matcher matcher = pattern.matcher(pwd);
		int n = 0;
		while(matcher.find()){
			n++;
		}
		return n*3;
	}
	

	
	
	
}
