package ua.com.zinchenko.Tasks;

public class Task3 {

    private final int lessonTimeInMin;
    private final int oddDelayTimeInMin;
    private final int evenDelayTimeInMin;

    public Task3() {
        lessonTimeInMin = 45;
        oddDelayTimeInMin = 5;
        evenDelayTimeInMin = 15;
    }

    public String getTimeEndLessonInStrFormat(int lessonNum) {
        int allLessonsTimeInMin = lessonNum * lessonTimeInMin;
        int allOddDelaysTimeInMin = (lessonNum / 2) * oddDelayTimeInMin;
        int allEvenDelaysTimeInMin = (lessonNum - (lessonNum / 2) - 1) * evenDelayTimeInMin;
        int allTimeSpanInSchool = allEvenDelaysTimeInMin +
                allOddDelaysTimeInMin + allLessonsTimeInMin;

        int hours = 9 + (allTimeSpanInSchool / 60);
        int minutes = allTimeSpanInSchool % 60;

        return  hours + " " + minutes;
    }
}
