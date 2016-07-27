Tu są wszystke url na ten moment:

ChapterController:
/api/courses/{courseId}/chapters - Gets list of Chapters by courseID
                                 - Posts Chapter
/api/courses/{courseId}/chapters/{chapterId} - Puts, Deletes Chapter

CourseController:
/api/courses - Gets list of Courses, Puts Course
/api/courses{courseId} - Pust Course, Deletse Course

ExerciseController:
/api/chapters/{chapterId}/exercises - gets list of Exercises by ChapterId,
									- posts Exercise
/api/chapters/{chapterId}/exercises/{exerciseId} - puts, deletes Exercise

LectureController:
/api/chapters/{chapterId}/lectures - gets list of lectures by chapterId, 
                                   -posts Lecture
/api/chapters/{chapterId}/lectures/{lectureId} - puts, deletes Lecture

QuestionController:
/api/exercises/{exerciseId}/questions - gets list of questions by exerciseId}
								      - posts Question
/api/exercises/{exerciseId}/questions/{questionId}/answers - adds new Answer to Question
/api/exercises/{exerciseId}/questions/{questionId}/ - puts Question

UserExerciseController:
/api/exercises/{exerciseId}/start  - start test
/api/userExercises/{userExerciseId}/{questionId} - adds User Answer to test
/api/userExercises/{userExerciseId}/stop - returns correct answers and user answer								  

NIE O TAKIE POLSKIE WALCZYŁEM ~LW