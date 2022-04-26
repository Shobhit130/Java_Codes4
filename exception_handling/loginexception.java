package exception_handling;
import java.util.Scanner;
public class loginexception {
	public static void main(String[] args) {
		String username,password;
		Scanner input = new Scanner(System.in);
		System.out.println("Enter the username");
		username = input.next();
		System.out.println("Enter the password");
		password = input.next();
		int res = checkPass(password,username);
		if(res == 0) {
			System.out.println("Password valid");
		}else {
			System.out.println("Password not valid");
		}
		
	}
	public static int checkPass(String pass,String user) {
		int flag = 0;
		try {
			if(pass.length()<8) {
				flag = 1;
				throw new LoginException("Minimum password length should be 8");
			}
			for(int i=0;i<pass.length();i++) {
				if((Character.isUpperCase(pass.charAt(i)))) {
					flag = 0;
					break;
				}else {
					flag = 1;
				}
			}
			if(flag!=0) {
				flag = 1;
				throw new LoginException("Atleast one uppercase letter should be there");	
			}
			for(int i=0;i<pass.length();i++) {
				if(((pass.charAt(i)=='*')||(pass.charAt(i) == '$') || (pass.charAt(i)=='#') || (pass.charAt(i)=='!'))) {
					flag = 0;
					break;
				}else {
					flag = 1;
				}
			}
			if(flag != 0) {
				flag = 1;
				throw new LoginException("Atleast one special symbol should be there");
			}
			if(pass.contains(user)) {
				flag = 1;
				throw new LoginException("Password should not contain username");
			}
			if(flag == 0) {
				try {
					int rand = (int)(Math.random()*20)+1;
					System.out.println("Captcha: "+rand);
					int random;
					Scanner input = new Scanner(System.in);
					System.out.println("Enter the captcha");
					random = input.nextInt();
					if(random != rand) {
						flag = 1;
						throw new LoginException("Captcha doesn't match");
					}
				}
				catch(LoginException e) {
					System.out.println(e.getMessage());
				}
			}
		}
		catch(LoginException e) {
			System.out.println(e.getMessage());
		}
		return flag;
	}
}
class LoginException extends Exception{
	public LoginException(String s) {
		super(s);
	}
}