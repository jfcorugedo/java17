package com.jfcorugedo.java17;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;

import static org.assertj.core.api.Assertions.assertThat;

public class NewFeaturesTest {

    /** Enhanced messages in NullPointerException **/
    @Test
    void newMessageInNullPointerExceptions() {
        String name = "Java 17 workshop";
        String speaker = "Corugedo";
        Integer attendants = null;

        NullPointerException exception = assertThrows(
                NullPointerException.class,
                () -> System.out.printf(
                        "%s, this is %s and we are %d!", name.trim(), speaker.trim(), attendants.intValue()
                )
        );

        exception.printStackTrace();

        assertThat(exception.getMessage()).isEqualTo("Cannot invoke \"java.lang.Integer.intValue()\" because \"attendants\" is null");
    }

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

        //
        //TIP:
        //var is not a reserved word, but a reserved type name.
        //
    }

    void inferringTypeWithVarProblemsWithPrimitives() {

        boolean test = true;
        var test2 = true;

        int i = 0;
        var i2 = 0;

        short s = 0;
        var s2 = 0;

        byte b = 0;
        var b2 = 0;

        long l = 0;
        var l2 = 0;
    }

    /** Switch expressions **/

    @Test
    void combineCaseValues() {

        switch (1) {
            case 1: case 2: case 3:
                System.out.println("Good");
                break;
            case 4:
                System.out.println("Bad");
                break;                
        }

        // Starting with Java 14 case values can be combined
        switch (1) {
            case 1, 2, 3:
                System.out.println("Good");
                break;
            case 4:
                System.out.println("Bad");
                break;
        }
    }

    @Test
    void switchExpressionBasicForm() {
        int dayOfTheWeek = 7;

        var name = switch (dayOfTheWeek) {
            case 1 -> "Monday";
            case 2 -> "Tuesday";
            case 3 -> "Wednesday";
            case 4 -> "Thursday";
            case 5 -> "Friday";
            case 6 -> "Saturday";
            case 7 -> "Sunday";
            default -> "Invalid day"; //Mandatory, it must cover all the possible values
        };

        System.out.printf(name);

        assertThat(name).isEqualTo("Sunday");
    }

    @Test
    void switchExpressionCaseBlocks() {
        int fish = 5;
        int length = 10;

        var name = switch (fish) {
            case 1 -> "Goldfish";
            case 2 -> {yield "Trout";}
            case 3 -> {
                if(length > 5) yield "Blobfish";
                yield "Green";
            }
            default -> "Swordfish";
        };

        System.out.println(name);

        assertThat(name).isEqualTo("Swordfish");
    }

    @Test
    void switchExpressionBasicFormWithoutReturn() {
        int month = 9;

        //It is no need to return anything
        switch (month) {
            case 1, 2, 3 -> System.out.println("Winter");
            case 4, 5, 6 -> System.out.println("Spring");
            case 7, 8, 9 -> System.out.println("Summer");
            case 10, 11, 12 -> System.out.println("Autumn");
            default -> System.out.println("Invalid month"); //It is not mandatory, we can remove it
        }

        //NOTE: There is no break statements anymore with switch expressions: Only one branch is executed
    }

    @Test
    void switchExpressionReturningConsistentDataTypes() {
        //Does this code compile?
        /*
        int measurement = 10;
        int size = switch(measurement) {
            case 5 -> 1;
            case 10 -> (short) 2;
            default -> 0;
            case 20 -> "3";
            case 40 -> 4L;
            case 50 -> null;
        };
         */


        /* RULE:
        All the branches of a switch expression that do not throw an exception
        must return a consistent data type (if the switch expression returns a value).
         */
    }

    @Test
    void switchExpressionReturningValueInEveryBranch() {
        //Does this code compile?
        /*
        int fish = 5;
        int length = 10;

        var name = switch (fish) {
            case 1 -> "Goldfish";
            case 2 -> {yield "Trout";}
            case 3 -> {
                if(length > 5) yield "Blobfish";
            }
            case 4 -> {}
            default -> "Swordfish";
        };
         */


        /* RULE:
        If the switch expression returns a value, then every
        branch that isn't an expression must yield a value.
         */
    }

    @Test
    void switchExpressionCoverAllPossibleValues1() {
        //Does this code compile?
        /*
        String canis = "dog";

        var type = switch (canis) {
            case "dog" -> 1;
            case "wolf" -> 2;
            case "coyote" -> 3;
        };
         */


        /* RULE:
        A default branch is required unless all cases
        are covered or no value is returned.
         */
    }

    @Test
    void switchExpressionCoverAllPossibleValues2() {
        //Does this code compile?
        /*
        enum Canis { DOG, WOLF, COYOTE };

        var animal = Canis.DOG;
        var type = switch (animal) {
            case DOG -> 1;
            case WOLF -> 2;
            case COYOTE -> 3;
        };
        */
    }

    @Test
    @Disabled("What does this test do?")
    void switchExpressionHandlingNullValues() {
        String langauge = null;

        var score = switch (langauge) {
            case "Java" -> 100;
            case "Javascript" -> 90;
            case "Python" -> 80;
            case "Kotlin" -> 80;
            default -> 0;
        };
    }


    /** Numeric literals **/
    void numericLiteralsWithUnderscores() {
        int million = 1_000_000;

        //Do these examples compile?

        //double atStart = _10000.0;

        //double atEnd = 10000.0_;

        //double byDecimal = 10000_.0;

        //double annoying = 1_00_0.0_0;

        //double reallyUgly = 1_____________2;

    }
}

class SuperCoolAndAwesomeIdentifierWithVeryLongNameDTO {}
