import java.util.HashSet;
import java.util.Set;

public class College {

    public static void invoke(){
        Set<Student> students = createNewStudents();

        printStudentsForCourse(students, 2);
        System.out.println("Переведено студентов на следующий курс: "+transferNextCourse(students));
        System.out.println("Отчислено студентов: "+removeBadStudents(students));
    }


    private static int transferNextCourse(Set<Student> students) {
        int count = 0;
        for (Student student : students) {
            if(student.moveNextCourse()){
                count++;
            }
        }
        return count;
    }

    private static int removeBadStudents(Set<Student> students) {
        int initialSize = students.size();
        students.removeIf(student -> student.getAverage() < Student.PASSING_GRADE);
        return initialSize - students.size();
    }

    private static Set<Student> createNewStudents() {
        Set<Student> students = new HashSet<>();
        students.add(new Student("Олег Козлов", "AA-1", 2, new int[]{2, 5, 4, 3, 4, 4, 5, 2, 3, 3}));
        students.add(new Student("Анна Смирнова", "BB-2", 1, new int[]{3, 4, 5, 2, 3, 5, 4, 4, 2, 5}));
        students.add(new Student("Иван Петров", "CC-3", 3, new int[]{5, 5, 4, 3, 2, 3, 5, 4, 4, 2}));
        students.add(new Student("Елена Иванова", "DD-4", 4, new int[]{4, 3, 5, 2, 5, 4, 3, 3, 2, 5}));
        students.add(new Student("Дмитрий Сидоров", "EE-5", 5, new int[]{3, 2, 2, 2, 3, 2, 3, 2, 2, 3}));
        students.add(new Student("Мария Федорова", "FF-1", 2, new int[]{2, 5, 5, 3, 4, 3, 2, 5, 4, 3}));
        students.add(new Student("Алексей Орлов", "GG-2", 1, new int[]{5, 4, 3, 2, 5, 5, 4, 3, 2, 5}));
        students.add(new Student("Оксана Лебедева", "HH-3", 3, new int[]{3, 2, 5, 4, 3, 5, 4, 2, 3, 5}));
        students.add(new Student("Сергей Волков", "II-4", 4, new int[]{2, 2, 3, 2, 2, 2, 3, 2, 2, 2}));
        students.add(new Student("Наталья Павлова", "JJ-5", 5, new int[]{4, 3, 5, 2, 5, 4, 3, 2, 5, 4}));
        return students;
    }

    private static void printStudentsForCourse(Set<Student> students, int course) {
        for (Student student : students) {
            if(student.getCourse()==course){
                System.out.println(student.getName());
            }
        }
    }
}
