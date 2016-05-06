package xmlValidator;

import com.beust.jcommander.JCommander;

public class XmlValidator {

    public static void main(String[] args){
        ValCommander val = new ValCommander();
        JCommander jct = null;
        try {
            jct = new JCommander(val, args);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.exit(1);
        }
        if(args.length == 0 || val.help){
            jct.usage();
            System.exit(0);
        }
        Validator validator = new Validator(val.xml, val.xsd, val.debug);
        validator.validate();
    }
}
