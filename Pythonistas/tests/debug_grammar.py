import sys
import shutil
import requests
from pathlib import Path
import os
from commons.utility import open_file, remove_char
import subprocess


def debug_grammar(path):
    """ Prints tokens and tree """
    string_input = open_file(path)
    output = create_temp_files(string_input)

    return output


def create_temp_files(string_input):
    """"""
    tmp_test_dir = Path(__file__).parent
    get_antlr(tmp_test_dir)

    ql_path_from = tmp_test_dir.joinpath('../grammar/QL.g4')
    ql_path_to = tmp_test_dir.joinpath('QL.g4')
    shutil.copyfile(ql_path_from, ql_path_to)

    # Generate the lexer and parser classes
    subprocess.check_output("""java -cp '{}/antlr-4.7-complete.jar' org.antlr.v4.Tool '{}/QL.g4'""".format(
        tmp_test_dir, tmp_test_dir), shell=True, universal_newlines=True)

    # Generate a java main class
    write_java_file(string_input)

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


def write_java_file(string_input):
    string_input = string_input.replace('"', '^')
    grammar = 'QL'

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
        String source = """ + remove_char(remove_char('"{}"'.format(repr(string_input)), 1), -2) + r""".replace("^", "\"");

        """ + repr(grammar)[1:-1] + """Lexer lexer = new File(source).exists() ?
                  new """ + repr(grammar)[1:-1] + """Lexer(CharStreams.fromString(source)) :
                  new """ + repr(grammar)[1:-1] + r"""Lexer(CharStreams.fromString(source));
          CommonTokenStream tokens = new CommonTokenStream(lexer);
          tokens.fill();
          System.out.println("\n[TOKENS]");
          for (Token t : tokens.getTokens()) {
              String symbolicName = """ + repr(grammar)[1:-1] + """Lexer.VOCABULARY.getSymbolicName(t.getType());
              String literalName = """ + repr(grammar)[1:-1] + r"""Lexer.VOCABULARY.getLiteralName(t.getType());
              System.out.printf("  %-20s '%s'\n",
                      symbolicName == null ? literalName : symbolicName,
                      t.getText().replace("\r", "\r").replace("\n", "\n").replace("\t", "\t"));
          }
          System.out.println("\n[PARSE-TREE]");
          """ + repr(grammar)[1:-1] + """Parser parser = new """ + repr(grammar)[1:-1] + """Parser(tokens);
          ParserRuleContext context = parser.form();
          String tree = context.toStringTree(parser);
          printPrettyLispTree(tree);
    }
}
""")
