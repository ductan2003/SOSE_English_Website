package com.elearningweb.library.service.impl;

import com.elearningweb.library.dto.PostDto;
import com.elearningweb.library.dto.CategoryDto;
import com.elearningweb.library.dto.ExamDto;
import com.elearningweb.library.model.Role;
import com.elearningweb.library.model.User;
import com.elearningweb.library.repository.PostRepository;
import com.elearningweb.library.repository.RoleRepository;
import com.elearningweb.library.repository.UserRepository;
import com.elearningweb.library.util.ExcelReadingUtil;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

//DATABASE INIT
//RUN ONCE THEN COMMENT
@Component
public class DbInit implements InitializingBean {
    @Autowired
    RoleRepository roleRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    BCryptPasswordEncoder passwordEncoder;
    @Autowired
    ExamServiceImpl examService;
    @Autowired
    ExcelReadingUtil excelReadingUtil;
    @Autowired
    FileServiceImpl fileService;
    @Autowired
    PostRepository postRepository;
    @Autowired
    PostServiceImpl postService;


    List<Role> roles = new ArrayList<>();

    @Override
    public void afterPropertiesSet() throws Exception {
        //CREATE ROLE
        if (roleRepository.findByName("ROLE_ADMIN") == null
                && roleRepository.findByName("ROLE_USER") == null) {
            roles.add(new Role("ROLE_ADMIN"));
            roles.add(new Role("ROLE_USER"));
            roleRepository.saveAll(roles);
        }
        //CREATE USERS TEST

        String password = passwordEncoder.encode("1234");
        if (userRepository.findByUserName("admin") == null) {
            userRepository.save(new User("He",
                    "He",
                    "admin",
                    password,
                    roles));
        }
        if (userRepository.findByUserName("user1") == null) {
            userRepository.save(new User("Tan Duc",
                    "Nguyen",
                    "user1",
                    password,
                    List.of(roleRepository.findByName("ROLE_USER"))));
        }
        if (userRepository.findByUserName("user2") == null) {
            userRepository.save(new User("Ha",
                    "Nguyen",
                    "user2",
                    password,
                    List.of(roleRepository.findByName("ROLE_USER"))));
        }
        if (userRepository.findByUserName("user3") == null) {
            userRepository.save(new User("Duong",
                    "Nguyen",
                    "user3",
                    password,
                    List.of(roleRepository.findByName("ROLE_USER"))));
        }
        if (userRepository.findByUserName("user4") == null) {
            userRepository.save(new User("Bach",
                    "Khi",
                    "user4",
                    password,
                    List.of(roleRepository.findByName("ROLE_USER"))));
        }
        if (userRepository.findByUserName("user5") == null) {
            userRepository.save(new User("Hieu",
                    "Pham",
                    "user5",
                    password,
                    List.of(roleRepository.findByName("ROLE_USER"))));
        }

        //INIT FILE FOLDER
        fileService.init();

        //CREATE TEST EXAMS
        examService.save(new ExamDto(
                "READING TEST 1",
                "Manage your time effectively to ensure that you can answer all questions within the allotted time.",
                "2023",
                "static/fileQuestion/test1.xlsx",
                "48c73d1f-ba97-4ee4-8539-6642440273eb.jpg",
                new CategoryDto(null, "Reading")));
        examService.save(new ExamDto(
                "READING TEST 2",
                "For short-answer questions, show all your work and provide clear and concise answers.",
                "2022",
                "static/fileQuestion/test2.xlsx",
                "48c73d1f-ba97-4ee4-8539-6642440273eb.jpg",
                new CategoryDto(null, "Reading")));
        examService.save(new ExamDto(
                "READING TEST 3",
                "Read all instructions and questions carefully before beginning.",
                "2022",
                "static/fileQuestion/test3.xlsx",
                "48c73d1f-ba97-4ee4-8539-6642440273eb.jpg",
                new CategoryDto(null, "Reading")));
        examService.save(new ExamDto(
                "READING TEST 4",
                "Turn in your exam and answer sheet to the invigilator before leaving the exam room.",
                "2023",
                "static/fileQuestion/test4.xlsx",
                "48c73d1f-ba97-4ee4-8539-6642440273eb.jpg",
                new CategoryDto(null, "Reading1")));
        examService.save(new ExamDto(
                "READING TEST 5",
                "Short-answer questions: points awarded based on the correctness, clarity, and completeness of the answers provided.",
                "2023",
                "static/fileQuestion/test5.xlsx",
                "48c73d1f-ba97-4ee4-8539-6642440273eb.jpg",
                new CategoryDto(null, "Reading")));


        postService.savePost(new PostDto("Actual tests in May 11th, 2023 Writing Task 2 (paper-based): Opinion",
                        "You should spend about 40 minutes on this task.\n" +
                                "\n" +
                                "Advertising is becoming more and more common in everyday life. Is it a positive or negative development?\n" +
                                "\n" +
                                "You should write at least 250 words.\n" +
                                "\n" +
                                "Sample: \n" +
                                "\n" +
                                "Advertising activities have become an integral part of our lives. They can be found everywhere, from billboards and magazines to social media and television. " +
                                "One of the advantages of advertising activities on people is that they help in informing and educating them about various products and services. Advertising helps to increase consumer knowledge about new products and services, and how they can benefit from them. By doing so, advertising can empower people to make informed purchasing decisions. Advertising can also be beneficial to people by creating a sense of excitement and desire for products and services. This can stimulate consumer interest in purchasing certain goods, and may lead to increased sales for businesses. Additionally, advertising can contribute to economic growth by creating jobs and generating revenue for businesses, which can have a positive impact on people's livelihoods.\n" +
                                "\n" +
                                "In conclusion, while we cannot deny the benefits of advertisements, the drawbacks they brought are truly more concerning in the long run.",
                        "Advertising is becoming more and more common in everyday life. Is it a positive or negative development?"),
                "2.jpg");

        postService.savePost(new PostDto("Actual tests in May 11th, 2023 Writing Task 1 (paper-based): Barchart",
                        "You should spend about 20 minutes on this task.\n" +
                                "\n" +
                                "The chart below compare the number of people per household* in the UK in 1981 and 2001. \n" +
                                "Summarize the information by selecting and reporting the main features, and make comparisons where relevant.\n" +
                                "\n" +
                                "You should write at least 150 words.\n" +
                                "\n" +
                                "Sample: \n" +
                                "\n" +
                                "The charts illustrate the percentage of different sized households in the UK, in 1981 and 2001.\n" +
                                "\n" +
                                "(150 words)",
                        "The chart below compare the number of people per household* in the UK in 1981 and 2001."),
                "3.jpg");

        postService.savePost(new PostDto("Actual tests in April 22nd, 2023 Writing Task 1 (paper-based): Line Graph",
                        "The line graph shows the information average number of visitors entering a museum in summer and winter in 2003.\n" +
                                "\n" +
                                "WRITING TASK 1\n" +
                                "You should spend about 20 minutes on this task.\n" +
                                "Looking at the comparison between the two seasons, it is worth noting that the number of visitors in the summer season was around 3-4 times higher than in the winter season. This difference is evident throughout the entire year, with the number of visitors reaching its peak only during the summer season.",
                        "The line graph shows the information average number of visitors entering a museum in summer and winter in 2003."),
                "4.jpg");

        postService.savePost(new PostDto("How to Do MCQ Type of Questions in ILETS Listening?",
                        "Did you notice a spelling error in this article title? Yes, you are right! It should be written as IELTS Listening, not ILETS Listening. The reason why this spelling error is highlighted in the title is to draw your attention towards the word “IELTS” which is commonly misspelled as ILETS.\n" +
                                "Keeping the spelling aside now, let us come to point for the purpose behind writing this article, to discuss how MCQs type of questions can be solved.\n" +
                                "In the IELTS Listening module, the most difficult question is said to be MCQ (Multiple Choice Question) i.e. question along with some options to choose from. In some questions, only one option is correct while in some of the other questions, more than one options are correct.\n" +
                                "In the above question, “two things” and “tour cost” are the major keywords that you can concentrate at. From the options, “accommodation” and “picnic” are two “one word” options to easily focus at, however, in the other three options, you can put more emphasis on words such as “fishing”, “tennis” and “meal” though you can not completely ignore the other words also in the options.",
                        " Listening Tips46,96705/10/2018\n"),
                "5.jpg");

        postService.savePost(new PostDto("How to Do Matching Heading Type of Questions in IELTS Listening?",
                        "One of the tricky questions apart from multiple choice questions in IELTS listening are the matching heading type of questions. As it can be determined by the term itself, matching heading involves matching a list of questions to another list of options. In these questions, you may find one-to-one or one-to-many type of questions. One-to-one matching heading questions mean that if question no. 1 matches with option no. C, then C cannot be the answer for any other question. However, one-to-many type of questions mean that if question no. 1 matches with option no. C, then C can also be the answer to another question. Here comes the difficulty in solving such questions. Hence, one-to-many questions are comparatively difficult than one-to-one questions.",
                        "Apart from all, you can make sure to keep your attention on understanding the audio conversation in IELTS listening."),
                "1.jpg");

        postService.savePost(new PostDto("How to Do Completion Type of Questions in IELTS Listening?",
                        "In the completion type of questions in IELTS listening, you are given short statements which are kind of short notes on the basis of audio conversation you are going to listen to and the blanks are some important information in the audio. One of the most effective tips that work out very well for such questions is to underline words that are near the blanks because you are most likely to hear those words in the audio as well, in the same order as you find in the question. Hence, you are thus able to identify your answers from the audio easily. For example,\n" +
                                "\n" +
                                "In the above question, you can see that the underlined words are closer to the blank and hence you should always keep these words in mind while listening to audio conversation. Apart from this, you can also highlight other words which can help you to understand the statements and focus",
                        "In the IELTS listening exam, you can find completion type of questions in the last section i.e. Section 4. "),
                "7.jpg");

        postService.savePost(new PostDto("How to Crack Section 3 of IELTS Listening?",
                        "Let us now discuss in detail how to crack Section 3 of IELTS listening test:\n" +
                                "In IELTS Listening Section 3, you can commonly find any of the questions such as table completion, MCQs (Multiple Choice Questions), matching headings, flow chart or diagram. However, form filling and sentence completion are not usually seen in this section.\n" +
                                "recruited hundred more employees\n" +
                                "was the highly skilled professional\n" +
                                "could reach out to maximum customers\n" +
                                "In the above type of question where it is comparatively difficult to concentrate, you can underline keywords as highlighted above. Now, let us find some important points to be noted for solving IELTS Listening Section 3.",
                        "IELTS Listening module consists of 4 sections viz."),
                "8.jpg");

        postService.savePost(new PostDto("378 High Frequency Words in IELTS Listening",
                        "1. Shelf\n" +
                                "\n" +
                                "2. Librarian\n" +
                                "\n" +
                                "3. The stacks\n" +
                                "\n" +
                                "4. Return\n" +
                                "\n" +
                                "5. Fine\n" +
                                "\n" +
                                "6. Magazine\n" +
                                "\n" +
                                "7. Copier \n" +
                                "\n" +
                                "8. Overdue \n" +
                                "\n" +
                                "9. Reading room \n" +
                                "\n" +
                                "10. Reference room \n" +
                                "\n" +
                                "11. Periodical room \n" +
                                "\n" +
                                "12. Study lounge \n" +
                                "\n" +
                                "\n" +
                                "23. Latest number \n" +
                                "\n" +
                                "24. Writing permission \n" +
                                "\n" +
                                "25. Check out \n" +
                                "\n" +
                                "26. Put on reserve ",
                        "Knowing what kind of vocabulary to expect in IELTS listening can greatly improve your performance in the actual exam. " +
                                "Come and see if you know the meaning of all 378 high frequency words."),
                "9.png");

        postService.savePost(new PostDto("How to Do Flow Chart Type of Questions in IELTS Listening?",
                        "Audio Information Always Flows in Sequence\n" +
                                "\n" +
                                "Like in all the questions in the IELTS listening module, you can keep in mind that the information in the audio always flows in the order of questions in the flow chart. Since flow chart is in the form of short steps, you need to focus on the important information in the audio and avoid any extended information or extra information. If you follow the order, it would be too easy for you to solve the flow chart question easily.\n" +
                                "\n" +
                                "Underline Keywords in the Sentences\n",
                        "You may come across flow chart type of questions in IELTS listening exam, probably in Section 2. "),
                "10.jpg");

        postService.savePost(new PostDto("Three things you must get right in IELTS Listening",
                        "Imagine that you are listening for the answer to Question 3, and you suddenly hear the answer to Question 6. This means you have been focusing so hard on one question that you have missed not only that one, but two more!\n" +
                                "\n" + "The key to doing well in the Listening test is to make it a two-stage process. First listen and make notes, then transfer your answers to the answer paper. In the Listening test, you need to be very precise when answering the questions. For example, let’s say the question paper asks you to complete the phrase ‘in the…’ and the answer is ‘morning’. When you write the information on the answer sheet, only ‘morning’ will be marked correct. If you write ‘the morning’ or ‘in the morning’ you will not be given a point (despite the fact that you know the answer). This rule also applies to spelling.",
                        "Most people find the Listening test more stressful than the Reading test. "),
                "11.jpg");
    }
}
