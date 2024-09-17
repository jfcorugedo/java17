package com.jfcorugedo.java17;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.assertj.core.api.Assertions.assertThat;

public class NewFeaturesTest {

    /** Text blocks **/

    @Test
    void understandTextBlocks() {
        String text = """
                {
                    "name": "Juan",
                    "lastname": "Corugedo"
                }
                """;

        System.out.println(text);

        assertThat(text).isEqualTo("{\n    \"name\": \"Juan\",\n    \"lastname\": \"Corugedo\"\n}\n");
    }

    @Test
    void textBlocks2() {
        String text = """
                  *
                 * *
                * * *
                """;

        System.out.println(text);

        assertThat(text).isEqualTo("  *\n * *\n* * *\n");
    }

    @Test
    void textBlocksHowManyLines1() {
        String text = """
                one
                two""";

        System.out.println(text);

        assertThat(text.lines().count()).isEqualTo(2);
        assertThat(text).isEqualTo("one\ntwo");
    }

    @Test
    void textBlocksHowManyLines2() {
        String text = """
                one
                two
                """;

        System.out.println(text);

        assertThat(text.lines().count()).isEqualTo(2);
        assertThat(text).isEqualTo("one\ntwo\n");
    }

    @Test
    void textBlocksHowManyLines3() {
        String text = """
                one
                two
               """;

        System.out.println(text);

        assertThat(text.lines().count()).isEqualTo(2);
        assertThat(text).isEqualTo(" one\n two\n");
    }

    @Test
    void textBlocksHowManyLines4() {
        String text = """
                one \
                two""";

        System.out.println(text);

        assertThat(text.lines().count()).isEqualTo(1);
        assertThat(text).isEqualTo("one two");
    }

    @Test
    void textBlocksHowManyLines5() {
        String text = """
                one \n
                two
                """;

        System.out.println(text);

        assertThat(text.lines().count()).isEqualTo(3);
        assertThat(text).isEqualTo("one \n\ntwo\n");
    }

    @Test
    void textBlocksSpecialCharacters() {
        String text = """
                one \"""
                two \"\"\"
                """;

        System.out.println(text);

        assertThat(text).isEqualTo("one \"\"\"\ntwo \"\"\"\n");
    }

    @Test
    void textBlocksSpacesAtTheEnd() {
        String text = """
                one     
                two     
                """;

        System.out.println(text);

        assertThat(text).isEqualTo("one\ntwo\n");
    }

    /** Inferring type with var **/

    void inferringTypeWithVar() {
        var number = 1;
        var name = "Juan";
        var list = new ArrayList<String>();
        var dto = new SuperCoolAndAwesomeIdentifierWithVeryLongNameDTO();
    }

    void inferringTypeWithVarCommonProblems1() {

        //Does this code compile?
        /*
        class Sample {
            var name = "";
        }
         */
    }

    void inferringTypeWithVarCommonProblems2() {

        //Does this code compile?
        /*
        var number = 7;
        number = 4;
        number = "five";
         */
    }

    void inferringTypeWithVarCommonProblems3() {

        //Does this code compile?
        /*
        var number;
        number = 4;
        var answer;
        if(number > 0) answer = true;
        else answer = false;
         */
    }

    void inferringTypeWithVarCommonProblems4() {

        //Does this code compile?
        /*
        int i=0, x=1;
        var n=2, z=3;
         */
    }

    void inferringTypeWithVarCommonProblems5() {

        //Does this code compile?
        /*
        var object = null;
         */
    }

    void inferringTypeWithVarCommonProblems6() {

        //Does this code compile?
        /*
        class Sample {
            void test(var name){
                System.out.println(name);
            }
        }
         */
    }

    void inferringTypeWithVarCommonProblems7() {

        //Does this code compile?
        /*
        class Var {
            public void var() {
                var var = "var";
            }

            public void Var() {
                Var var = new Var();
            }
        }
         */




        //var is not a reserved word, but a reserved type name.
    }
}

class SuperCoolAndAwesomeIdentifierWithVeryLongNameDTO {}
