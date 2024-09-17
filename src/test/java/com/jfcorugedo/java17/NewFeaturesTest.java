package com.jfcorugedo.java17;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

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


    /** Records **/
    @Test
    void basicRecordDeclaration() {

        record Person(String name, String surname, int age) {}

        //All args constructor
        var juan  = new Person("Juan", "Corugedo", 41);

        //Accessor methods
        System.out.printf("Hi! my name is %s and I'm %d years old\n", juan.name(), juan.age());

        //equals and hashCode
        var copy = new Person(juan.name(), juan.surname(), juan.age());
        assertThat(copy).isEqualTo(juan);
        assertThat(copy.hashCode()).isEqualTo(juan.hashCode());

        //toString
        System.out.println(juan);
        assertThat(juan.toString()).isEqualTo("Person[name=Juan, surname=Corugedo, age=41]");
    }

    @Test
    void recordCanBeEmpty() {
        record Empty() {}

        var pointless = new Empty();

        System.out.println(pointless);
    }

    @Test
    void recordsAreImplicitlyFinal() {
        record MyRecord() {}

        //Does it compile?
        //record DoesItCompile() extends MyRecord{}

        //Do these records compile?
        //record MySerialDTO() implements Serializable {}
        /*
        record MyOtherRecord() implements Comparable<MyOtherRecord> {
            @Override
            public int compareTo(MyOtherRecord o) {
                return 0;
            }
        }
         */
    }

    @Test
    void recordsLongConstructor() {
        record Developer(String name, List<String> skills) {
            public Developer(String name, List<String> skills) {
                if(skills == null || skills.isEmpty()) {
                    throw new IllegalArgumentException();
                }
                this.name = name; //Mandatory, since all the fields are final
                this.skills = skills; //Mandatory
            }
        }

        System.out.println(new Developer("Pablo", List.of("Java", "Kotlin")));

        /* RULE:
        The compiler will not insert a constructor if you define
        one with the same list of parameters in the same order.
         */
    }

    @Test
    void recordsCompactConstructor() {
        //Compact constructors does not need to define all the fields
        record Developer(String name, List<String> skills) {
            Developer {
                if(skills == null) skills = new ArrayList<>();
                if(name == null) name = "noname";

                name = name.substring(0,1).toUpperCase() + name.substring(1).toLowerCase();

                //Java will execute full constructor after this compact constructor
            }
        }

        var miguel = new Developer("miguel", List.of("Java", "Python"));
        System.out.println(miguel);

        assertThat(miguel.name()).isEqualTo("Miguel");
    }

    @Test
    void recordsCompactConstructorMidifyingFields() {
        record Person(String name, String surname, int age) {
            Person {
                //Does it compile?
                //this.name = name.toUpperCase();
            }
        }
    }

    @Test
    void recordsOverloadedConstructors() {
        record Person(String name, String surname, int age) {
            public Person(String firstName, String lastName, String surename, int age) {
                //First line must be a call to another constructor
                this("%s %s".formatted(firstName, lastName), surename, age);

                //Do these sentences compile?
                // age = 20;
                // this.age = 20;
            }
        }
    }

    @Test
    void recordsOverloadingMethods() {
        record Person(String name, String surname, int age) {
            @Override public String toString() { return name; }
            @Override public int age() {
                System.out.println("Getting the age!");
                return age;
            }
        }

        System.out.println(new Person("María", "García", 35));
    }

    @Test
    void recordsOtherFields() {
        record Person(String name, String surname, int age) {
            //Do these fields declaration compile?

            //public String nick;
            //private int score;
            //public static String type;
        }

        System.out.println(new Person("María", "García", 35));
    }

    /* if pattern matching */
    @Test
    void ifPatternMatchingBasis() {
        Object object = 1;

        // Old way
        if(object instanceof Integer) {
            Integer number = (Integer) object;
            System.out.println(number.compareTo(5));
        }

        // New way (Java 16):
        if(object instanceof Integer number) {
            System.out.println(number.compareTo(5));
        }
    }

    @Test
    void ifPatternMatchingResigning() {
        Object object = 1;

        if(object instanceof Integer number) {
            number = 10;// Bad practice
            System.out.println(number.compareTo(5));
        }

        if(object instanceof final Integer number) {
            // number = 10; //Does not compile
            System.out.println(number.compareTo(5));
        }
    }

    @Test
    void ifPatternMatchingExpressions() {
        Object object = 1;

        if(object instanceof Integer age && age < 18) {
            System.out.println("This user is a minor!");
        }
    }

    @Test
    void ifPatternMatchingMustUseSubtypes() {
        Number number = 1;

        if(number instanceof Integer age && age.compareTo(18) < 0) {
            System.out.println("This user is a minor!");
        }

        //Do these statements compile?
        // if(number instanceof Number other){}
        // if(number instanceof List list) {}
    }

    @Test
    void ifPatternMatchingFlowScoping() {

        // RULE:
        // Flow scoping means the variable is only in scope when
        // the compiler can definitively determine its type.

        Number number = 1;

        //Do these code snippets compile?
        /*
        if(number instanceof Integer age || age.compareTo(18) < 0) {
            System.out.println("This user is a minor!");
        }
        */

        /*
        if(number instanceof Integer age)
            age.compareTo(5);
        age.compareTo(18);
        */

        /*
        if(!(number instanceof Integer age)) {
            return;
        }
        age.compareTo(10);
        */
    }
}

class SuperCoolAndAwesomeIdentifierWithVeryLongNameDTO {}
