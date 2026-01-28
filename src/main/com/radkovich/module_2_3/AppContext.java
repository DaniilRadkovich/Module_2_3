package main.com.radkovich.module_2_3;

import main.com.radkovich.module_2_3.controller.LabelController;
import main.com.radkovich.module_2_3.controller.PostController;
import main.com.radkovich.module_2_3.controller.WriterController;
import main.com.radkovich.module_2_3.repository.LabelRepository;
import main.com.radkovich.module_2_3.repository.PostRepository;
import main.com.radkovich.module_2_3.repository.WriterRepository;
import main.com.radkovich.module_2_3.repository.hibernate.HibernateLabelRepositoryImpl;
import main.com.radkovich.module_2_3.repository.hibernate.HibernatePostRepositoryImpl;
import main.com.radkovich.module_2_3.repository.hibernate.HibernateWriterRepositoryImpl;
import main.com.radkovich.module_2_3.service.LabelService;
import main.com.radkovich.module_2_3.service.PostService;
import main.com.radkovich.module_2_3.service.WriterService;
import main.com.radkovich.module_2_3.view.LabelView;
import main.com.radkovich.module_2_3.view.PostView;
import main.com.radkovich.module_2_3.view.WriterView;

import java.util.Scanner;

public class AppContext {
    private final WriterRepository writerRepository = new HibernateWriterRepositoryImpl();
    private final PostRepository postRepository = new HibernatePostRepositoryImpl();
    private final LabelRepository labelRepository = new HibernateLabelRepositoryImpl();

    private final WriterService writerService = new WriterService(writerRepository);
    private final PostService postService = new PostService(postRepository);
    private final LabelService labelService = new LabelService(labelRepository);

    private final WriterController writerController = new WriterController(writerService, postRepository);
    private final PostController postController = new PostController(postService, labelService, postRepository);
    private final LabelController labelController = new LabelController(labelService);

    private final WriterView writerView = new WriterView(writerController);
    private final PostView postView = new PostView(postController);
    private final LabelView labelView = new LabelView(labelController);

    Scanner scanner = new Scanner(System.in);

    public void launch() {
        System.out.println("Choose an option:");
        System.out.println("1: Writer");
        System.out.println("2: Post");
        System.out.println("3: Label");

        int option = scanner.nextInt();
        scanner.nextLine();

        switch (option) {
            case 1:
                writerStart();
                break;
            case 2:
                postStart();
                break;
            case 3:
                labelStart();
                break;
            default:
                System.out.println("Invalid option");
        }
    }

    public void writerStart() {
        writerView.run();
    }

    public void postStart() {
        postView.run();
    }

    public void labelStart() {
        labelView.run();
    }

}
