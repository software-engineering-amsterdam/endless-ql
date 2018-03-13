import sys
import shutil
import requests
from pathlib import Path
import os
from commons.utility import open_file
import subprocess


def debug_grammar(path):
    """ Prints tokens and tree """
    string_input = open_file(path)
    output = create_temp_files()

    return output


def create_temp_files():
    """"""
    tmp_test_dir = Path(__file__).parent
    get_antlr(tmp_test_dir)

    ql_path_from = tmp_test_dir.joinpath('../grammar/QL.g4')
    ql_path_to = tmp_test_dir.joinpath('QL.g4')
    shutil.copyfile(ql_path_from, ql_path_to)

    # Generate the lexer and parser classes
    # subprocess.check_output("""java -cp '{}/antlr-4.7-complete.jar' org.antlr.v4.Tool '{}/QL.g4'""".format(
    #     tmp_test_dir, tmp_test_dir), shell=True, universal_newlines=True)

    os.system('SET CLASSPATH=.;C:\\Javalib\\antlr-4.7.1-complete.jar;')
    subprocess.check_output("""java -cp '{0}\\antlr-4.7-complete.jar' org.antlr.v4.Tool '{1}\\QL.g4'""".format(
        tmp_test_dir, tmp_test_dir), shell=True, universal_newlines=True)

    # Generate a java main class
    write_java_file()

    # Compile all .java source files and run the main class
    subprocess.check_output("""javac -cp 'antlr-4.7-complete.jar:{}' *.java""".format(tmp_test_dir), shell=True,
                            universal_newlines=True, cwd=tmp_test_dir)

    output = subprocess.check_output("""java -cp '{}/antlr-4.7-complete.jar:{}' 'Main'""".format(
        tmp_test_dir, tmp_test_dir), shell=True, universal_newlines=True)

    clean_up()
    return output


def get_antlr(tmp_test_dir):
    """Gets antlr jar used for testing"""
    antlr = 'antlr-4.7-complete.jar'
    url = 'http://www.antlr.org/download/{}'.format(antlr)

    tmp_test_jar = tmp_test_dir.joinpath(Path(antlr).name)
    response = requests.get(url)
    tmp_test_jar.write_bytes(response.content)


def clean_up():
    dir_name = Path(__file__).parent
    test = os.listdir(dir_name)

    for item in test:
        if item.endswith(".java") | item.endswith(".class") | item.endswith(".tokens") | item.endswith(".jar") | \
                item.endswith(".g4"):
            os.remove(os.path.join(dir_name, item))


def write_java_file():
    with open('tests/Main.java', 'w') as out:
        out.write(r"""import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.Token;
import java.io.File;
import java.io.IOException;

public class Main {
    private static void printPrettyLispTree(String tree) {
        int indentation = 1;
        for (char c : tree.toCharArray()) {
            if (c == '(') {
                if (indentation > 1) {
                    System.out.println();
                }
                for (int i = 0; i < indentation; i++) {
                    System.out.print("  ");
                }
                indentation++;
            }
            else if (c == ')') {
                indentation--;
            }
            System.out.print(c);
        }
        System.out.println();
    }

    public static void main(String[] args) {
        String sourc = "form Box1HouseOwning {\n    ^Did you sell a house in 2010?^ hasSoldHouse:  money = (5 - 3)\n}";
        String source = sourc.replace("^", "\"");

        QLLexer lexer = new File(source).exists() ?
                  new QLLexer(CharStreams.fromString(source)) :
                  new QLLexer(CharStreams.fromString(source));
          CommonTokenStream tokens = new CommonTokenStream(lexer);
          tokens.fill();
          System.out.println("\n[TOKENS]");
          for (Token t : tokens.getTokens()) {
              String symbolicName = QLLexer.VOCABULARY.getSymbolicName(t.getType());
              String literalName = QLLexer.VOCABULARY.getLiteralName(t.getType());
              System.out.printf("  %-20s '%s'\n",
                      symbolicName == null ? literalName : symbolicName,
                      t.getText().replace("\r", "\r").replace("\n", "\n").replace("\t", "\t"));
          }
          System.out.println("\n[PARSE-TREE]");
          QLParser parser = new QLParser(tokens);
          ParserRuleContext context = parser.form();
          String tree = context.toStringTree(parser);
          printPrettyLispTree(tree);
    }
}
""")
