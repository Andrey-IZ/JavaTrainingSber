package com.socialnetwork.service;

import com.socialnetwork.dao.UserDao;
import com.socialnetwork.entity.User;
import com.socialnetwork.entity.interfaces.IUser;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.stubbing.Answer;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    UserService userService;
    List<IUser> fakeUserList;
    @Mock
    private UserDao mockedUserDao;


    @BeforeEach
    void setUp() {
        Logger logger = mock(Logger.class, (Answer<Void>) invocationOnMock -> {
            System.out.println(MessageFormat.format("{0}: {1}",
                    invocationOnMock.getMethod().getName(),
                    invocationOnMock.getArgument(0)));
            return null;
        });
        userService = new UserService(logger, mockedUserDao);
        fakeUserList = getFakeUsers();
    }

    List<IUser> getFakeUsers() {
        return new ArrayList<>(List.of(
                new User("John", "Wick", "wick@gmail.com", 44, "male", "USA", "Holliwood", "1234", "user"),
                new User("Bill", "Kogan", "kogan@gmail.com", 50, "male", "USA", "Holliwood", "qwerty", "user"),
                new User("Tom", "Crouse", "tomcr@gmail.com", 52, "male", "USA", "Holliwood", "1234", "user")
        ));
    }

    @Test
    void getAllUsers() {
        var users = getFakeUsers();
        when(mockedUserDao.getAllUser()).thenReturn(users);
        assertEquals(users, userService.getAllUsers());
        verify(mockedUserDao, times(1)).getAllUser();
    }

    @Test
    void getUserById() {
        IUser fakedUser = getFakeUsers().get(0);
        when(mockedUserDao.getUserById(1)).thenReturn(fakedUser);
        assertEquals(fakedUser, userService.getUserById(0));
        verify(mockedUserDao, times(1)).getUserById(0);
    }

    @Test
    void removeUserById() {
        var users = getFakeUsers();
        var userId = 0;
        List<IUser> fakedUsers = users.stream()
                .filter(user -> user.getId() != userId)
                .collect(Collectors.toList());
        userService.removeUserById(userId);
        assertEquals(fakedUsers, userService.getAllUsers());
        verify(mockedUserDao, times(1)).removeUserById(0);
    }

    @Test
    void updateUser() {
        var users = getFakeUsers();
        var newUser = mock(IUser.class);
        userService.updateUser(newUser);
        verify(mockedUserDao, times(1)).updateUser(newUser);
    }

    @Test()
    void getUsersByFirstName() {
        var firstName = "John";
        List<IUser> fakedUsers = fakeUserList.stream()
                .filter(user -> user.getFirstName().equals(firstName))
                .collect(Collectors.toList());
        when(mockedUserDao.getUsersByFirstName(firstName)).thenReturn(fakedUsers);
        assertEquals(fakedUsers, userService.getUsersByFirstName(firstName));
        verify(mockedUserDao, times(1)).getUsersByFirstName("John");
    }

    @Test
    void getUsersByLastName() {
    }

    @Test
    void getUsersByFullName() {
    }

    @Test
    void getUserByUserName() {
    }

    @Test
    void getUserByEmail() {
    }

    @Test
    void getUsersByAge() {
    }

    @Test
    void getUsersByGender() {
    }

    @Test
    void getUsersByCountry() {
    }

    @Test
    void getUsersByCity() {
    }

    @Test
    void register() {
        var newUser = new User("Josh", "Tornton", "asd21@gmail.com", 21, "male", "USA", "Holliwood", "5464", "newUser");

        var userInDao = new ArrayList<>(fakeUserList);
        var expected = new ArrayList<>(fakeUserList);
        expected.add(newUser);
        doAnswer(invocationOnMock -> {
            var userArg = invocationOnMock.getArgument(0);
            assertEquals(newUser, userArg);
            userInDao.add(newUser);
            return null;
        }).when(mockedUserDao).insertUserToDb(any(IUser.class));

        userService.register(newUser);
        assertEquals(expected, userInDao);
    }
}