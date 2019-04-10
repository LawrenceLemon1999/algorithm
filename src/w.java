import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class w {
    static class Person{
        public String name;
        public  int age;

        public Person(String name, int age) {
            super();
            this.name = name;
            this.age = age;
        }
        public String getName() {
            return name;
        }
        public void setName(String name) {
            this.name = name;
        }
        public int getAge() {
            return age;
        }
        public void setAge(int age) {
            this.age = age;
        }
        @Override
        public String toString() {
            return "Person [name=" + name + ", age=" + age + "]";
        }
    }


   static class NameComparator implements Comparator<Person> {

        @Override
        public int compare(Person o1, Person o2) {
            return o1.getName().compareTo(o2.getName());
        }
    }

    public static void main(String[] args) {

        List<Person> list=new ArrayList<Person>();

        Person p1=new Person("d",55);
        Person p2=new Person("c",18);
        Person p3=new Person("a",37);

        list.add(p1);
        list.add(p2);
        list.add(p3);
        sortPerson(list);
    }

    public static void sortPerson(List<Person> list){

        Collections.sort(list,new NameComparator());
        for (Person person : list) {
            System.out.println(person);
        }
    }
}