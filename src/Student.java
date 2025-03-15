public class Student {
    public static final int MAX_COURSE = 5;
    public static final double PASSING_GRADE = 3.0;

    private String name;
    private String group;
    private int course;
    private int [] grades;

    public Student(String name, String group, int course, int [] grades) {
        this.name = name;
        this.group = group;
        this.course = course;
        this.grades = grades;
    }

    public boolean moveNextCourse(){
        if(getAverage()>= PASSING_GRADE && course<MAX_COURSE){
            course++;
            return true;
        }
        return false;
    }

    public double getAverage() {
        int sum = 0;
        for (int grade : grades) {
            sum += grade;
        }
        return (double) sum / grades.length;
    }

    public int getCourse() {
        return course;
    }

    public String getName() {
        return name;
    }
}
