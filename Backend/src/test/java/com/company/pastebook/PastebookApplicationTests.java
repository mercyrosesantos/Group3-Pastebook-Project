package com.company.pastebook;

import com.company.pastebook.controllers.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class PastebookApplicationTests {

	@Autowired
	private AlbumController albumController;

	@Autowired
	private FriendRequestController friendRequestController;

	@Autowired
	private NotificationController notificationController;

	@Autowired
	private PhotoController photoController;

	@Autowired
	private PostController postController;

	@Autowired
	private ReactionController reactionController;

	@Autowired
	private SearchController searchController;

	@Autowired
	private UserController userController;



	@Test
	void albumLoads() {
		assertThat(albumController).isNotNull();
	}

	@Test
	void friendRequestLoads() {
		assertThat(friendRequestController).isNotNull();
	}

	@Test
	void notificationLoads() {
		assertThat(notificationController).isNotNull();
	}

	@Test
	void photoLoads() {
		assertThat(photoController).isNotNull();
	}

	@Test
	void postLoads() {
		assertThat(postController).isNotNull();
	}

	@Test
	void reactionLoads() {
		assertThat(reactionController).isNotNull();
	}

	@Test
	void searchLoads() {
		assertThat(searchController).isNotNull();
	}


	@Test
	void userLoads() {
		assertThat(userController).isNotNull();
	}

}
