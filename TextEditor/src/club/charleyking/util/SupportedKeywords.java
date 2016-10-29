package club.charleyking.util;

import java.util.ArrayList;

/**
 * A class to store the programming language keywords and
 * provide access to them.
 *
 * Makes multiple language support possible and makes adding new language
 * support convenient. To add more keywords, add a string array and getters
 * to this class. Then, update the switch statement in UI.java.
 */
public class SupportedKeywords {
    private String[] javaKeywords = {"abstract", "assert", "boolean",
            "break", "byte", "case", "catch", "char", "class", "const",
            "continue", "default", "do", "double", "else", "extends", "false",
            "final", "finally", "float", "for", "goto", "if", "implements",
            "import", "instanceof", "int", "System", "out", "print()", "println()",
            "new", "null", "package", "private", "protected", "public", "interface",
            "long", "native", "return", "short", "static", "strictfp", "super", "switch",
            "synchronized", "this", "throw", "throws", "transient", "true",
            "try", "void", "volatile", "while", "String"};

    private String[] cppKeywords = { "auto", "const", "double", "float", "int", "short",
                "struct", "unsigned", "break", "continue", "else", "for", "long", "signed",
                "switch", "void", "case", "default", "enum", "goto", "register", "sizeof",
                "typedef", "volatile", "char", "do", "extern", "if", "return", "static",
                "union", "while", "asm", "dynamic_cast", "namespace", "reinterpret_cast", "try",
                "bool", "explicit", "new", "static_cast", "typeid", "catch", "false", "operator",
                "template", "typename", "class", "friend", "private", "this", "using", "const_cast",
                "inline", "public", "throw", "virtual", "delete", "mutable", "protected", "true", "wchar_t" };

    private String[] brackets = { "{", "(" };
    private String[] bracketsCompletions = { "}", ")" };
    
    public String[] getJavaKeywords() {
        return javaKeywords;
    }
    public String[] getCppKeywords() {
        return cppKeywords;
    }
    public ArrayList<String> getBracketCompletions() {
        ArrayList<String> list = new ArrayList<>();
        for(String completion : bracketsCompletions) {
            list.add(completion);
        }
        return list;
    }
    
    public ArrayList<String> getBrackets() {
        ArrayList<String> list = new ArrayList<>();
        for(String completion : brackets) {
            list.add(completion);
        }
        return list;
    }
    
    public ArrayList<String> getKeywordsList(String[] string) {
        ArrayList<String> list = new ArrayList<>();
        for(String words : string) {
            list.add(words);
        }
        return list;
    }
}