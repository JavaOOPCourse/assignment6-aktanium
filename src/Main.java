import java.util.*;

public class Main {
    public static void main(String[] args) {

        HashMap<String, Student> students = new HashMap<>();

        // ====================== TASK 1 ======================
        // TODO: Добавь минимум 5 студентов (ключ = ID)
        // Сделай минимум два студента с одинаковым GPA (для Task 3)

        students.put("S1", new Student("Ali", 3.5, 20));
        students.put("S2", new Student("Bek", 3.8, 21));
        students.put("S3", new Student("Aida", 3.5, 19)); // same GPA
        students.put("S4", new Student("John", 3.2, 22));
        students.put("S5", new Student("Zara", 3.9, 20));

        // TODO: Напечатай всех студентов (ID + объект)

        System.out.println("All Students:");
        for (Map.Entry<String, Student> entry : students.entrySet()) {
            System.out.println(entry.getKey() + " → " + entry.getValue());
        }

        // TODO: Найди студента по ID и выведи его

        System.out.println("\nFind S2:");
        System.out.println(students.get("S2"));

        // TODO: Удали одного студента по ID

        students.remove("S4");

        // TODO: Обнови GPA у одного студента

        students.get("S1").setGpa(3.95);

        // ====================== SORTING (IMPORTANT) ======================
        // TODO: Создай ArrayList из всех студентов (students.values())

        List<Student> list = new ArrayList<>(students.values());

        // TODO 6a: Отсортируй по GPA (natural ordering) и выведи

        Collections.sort(list);

        System.out.println("\nSorted by GPA (ASC):");
        for (Student s : list) {
            System.out.println(s);
        }

        // TODO 6b: Отсортируй по имени (Comparator) и выведи

        list.sort(Comparator.comparing(Student::getName));

        System.out.println("\nSorted by Name:");
        for (Student s : list) {
            System.out.println(s);
        }

        // ====================== TASK 2 ======================
        System.out.println("\n=== Task 2: Top 3 by GPA ===");

        // TODO: Создай новый список, отсортируй по GPA по убыванию, выведи первые 3

        List<Student> topList = new ArrayList<>(students.values());
        topList.sort(Collections.reverseOrder());

        for (int i = 0; i < Math.min(3, topList.size()); i++) {
            System.out.println(topList.get(i));
        }

        // ====================== TASK 3 ======================
        System.out.println("\n=== Task 3: Students with same GPA ===");

        // TODO: Сгруппируй студентов по GPA и выведи только те, где больше 1 студента

        HashMap<Double, List<String>> gpaMap = new HashMap<>();

        for (Student s : students.values()) {
            gpaMap
                    .computeIfAbsent(s.getGpa(), k -> new ArrayList<>())
                    .add(s.getName());
        }

        for (Map.Entry<Double, List<String>> entry : gpaMap.entrySet()) {
            if (entry.getValue().size() > 1) {
                System.out.println("GPA " + entry.getKey() + " → " + entry.getValue());
            }
        }

        // ====================== TASK 4 ======================
        System.out.println("\n=== Task 4: Courses ===");

        HashMap<Course, List<Student>> courseMap = new HashMap<>();

        // TODO: Создай 2–3 курса, добавь студентов, выведи всё

        Course java = new Course("Java");
        Course math = new Course("Math");

        courseMap.put(java, new ArrayList<>());
        courseMap.put(math, new ArrayList<>());

        courseMap.get(java).add(students.get("S1"));
        courseMap.get(java).add(students.get("S2"));
        courseMap.get(math).add(students.get("S3"));

        for (Map.Entry<Course, List<Student>> entry : courseMap.entrySet()) {
            System.out.println(entry.getKey());
            for (Student s : entry.getValue()) {
                System.out.println("   " + s);
            }
        }

        // ====================== TASK 5 ======================
        System.out.println("\n=== Task 5: GPA desc + Name ===");

        // TODO: Создай Comparator (GPA убывание → если равно, то имя возрастание)
        // и отсортируй

        List<Student> customSort = new ArrayList<>(students.values());

        customSort.sort((a, b) -> {
            int gpaCompare = Double.compare(b.getGpa(), a.getGpa());
            if (gpaCompare == 0) {
                return a.getName().compareTo(b.getName());
            }
            return gpaCompare;
        });

        for (Student s : customSort) {
            System.out.println(s);
        }
    }
}