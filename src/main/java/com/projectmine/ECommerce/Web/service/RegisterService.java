package com.projectmine.ECommerce.Web.service;

import com.projectmine.ECommerce.Web.model.User;
import com.projectmine.ECommerce.Web.repository.RegisterRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class RegisterService {

    //Constructor Injection
    RegisterRepository repo;
    public RegisterService(RegisterRepository repo){
        this.repo = repo;
    }

    //We have two ways to check this:
    //1. Check karna ki kahi same name,email, ya password to database me already hai to nahi
    //2. Check karna ki kahi session.getAttribute to null nahi hai

    //I will prefer 1.
     public String setUser(User u){

         // Data already exists or not
         if(!repo.IsExists(u)){

             // Only store the data when it is valid
             if(isValidData(u)) {
                 BCryptPasswordEncoder hashPass = new BCryptPasswordEncoder(10);
                 u.setPassword(hashPass.encode(u.getPassword()));
                 repo.addUser(u);
                 return "SUCCESS";
             } else { return "INVALID_DATA"; }
         } else {
             return "EMAIL_ALREADY_EXISTS";
         }
     }

     public boolean isValidData(User u) {
         // When all three are valid
        if(isName(u.getName()) && isEmail(u.getEmail()) && isPassword(u.getPassword()))
            return true;
        else
            return false;
     }

     //-----------------------------------------NAME-----------------------------------------------------

     public boolean isName(String name) {
         //Removing extra spaces
         name = name.trim();
         int l = name.length();

         // Lower and Upper Limit of String name
         if(l <= 1 || l > 50) {
             return false;
         }

         // Traversing through each character
         for (int i = 0; i < l-1; i++) {
             char c = name.charAt(i);

             // Only Letters and Spaces in between are allowed
             if(!(Character.isLetter(c) || Character.isWhitespace(c)))
                 return false;

             // When space is repeated
             if(Character.isWhitespace(c) && Character.isWhitespace(name.charAt(i+1))){
                 return false;
             }
         }
         char last_char = name.charAt(l-1);   //Storing last character separately

         // Checking for last Character
         if(!(Character.isLetter(last_char) || Character.isWhitespace(last_char)))
             return false;

        return true;
     }

     //-----------------------------------------EMAIL ID--------------------------------------------------

     public boolean isEmail(String email){
        // Lower and Upper limit of String email
         if(email.length() <= 5 || email.length() > 50)
             return false;

         // Three parts of Email Id i.e local, domain, tdl
         String local_part=  "";
         String domain_part = "";
         String tdl_part = "";

         // The breaking points to separate out local, domain, tdl from the String email
         int localpoint = email.indexOf('@');
         int domainpoint = email.lastIndexOf('.');

         // Important traversing parameter
         String dummy = "";

         // Traversing through each character of the email
         for(int i = 0; i < email.length();i++) {
             char ch = email.charAt(i);           // index of each character
             dummy += ch;
             // Parameter of local part
             if (dummy.length()-1 < localpoint && dummy.length()-1 < domainpoint) {
                 local_part += ch;
             }
             // Parameter of domain part
             else if (dummy.length()-1 > localpoint && dummy.length()-1 < domainpoint) {
                 domain_part += ch;
             }
             // Skip the break point index
             else if (dummy.length()-1 == localpoint || dummy.length()-1 == domainpoint) {}
             // the remaining part is tdl
             else {
                 tdl_part += ch;
             }
         }
         System.out.println(local_part+"\t"+domain_part+"\t"+tdl_part);
         // returns true when all parts are valid
         return isLocal(local_part) && isDomain(domain_part) && isTdl(tdl_part);

     }

     // Check for Local part of the [EMAIL ID]
     public boolean isLocal(String local){
        if(local == null) return false;

        boolean flag = true;
         if(local.charAt(0) == '.' || local.charAt(local.length()-1) == '.') {
             flag = false;
         }
         if(local.lastIndexOf('.') - local.indexOf('.') == 1) {
             flag = false;
         }
         return flag;
     }

     // Check for Domain part of the [EMAIL ID]
    public boolean isDomain(String domain){
        if(domain == null) return false;

        String domainList[] = {"example","gmail","email","yahoo","outlook"};
        boolean flag = true;
        for(int i = 0; i < domainList.length;i++){
            if(domain.equals(domainList[i])){
                return true;
            } else {
                flag = false;
            }
        }
        return flag;
    }

    // Check for TDL part of the [EMAIL ID]
    public boolean isTdl(String tdl){
        if(tdl == null) return false;

        String tdlList[] = {"com","net","org","in","co.in","edu","io","hotmail","icloud"};
        boolean flag = true;
        for(int i = 0; i < tdlList.length;i++){
            if(tdl.equals(tdlList[i])){
                return true;
            } else {
                flag = false;
            }
        }
        return flag;
    }

    //-------------------------------------------PASSWORD-------------------------------------------------

    public boolean isPassword(String password){
        // Lower and Upper limit of the String password
        if(password.length() <= 4 || password.length() > 16)
            return false;
         return true;
    }

}
