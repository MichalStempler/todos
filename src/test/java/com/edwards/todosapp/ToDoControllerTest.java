package com.edwards.todosapp;


import com.edwards.todosapp.error.InvalidIdException;
import com.edwards.todosapp.model.ToDo;
import com.edwards.todosapp.model.TodoStatusEnum;
import com.edwards.todosapp.service.TodoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class ToDoControllerTest {

    private static String jsonAllToDos = "[{\"id\":1,\"title\":\"workout\",\"description\":\"go to pilates\",\"createdTime\":\"2024-03-13T17:15:03\",\"status\":\"TODO\"},{\"id\":2,\"title\":\"laundry\",\"description\":\"wash the kids pile\",\"createdTime\":\"2024-03-13T17:15:03\",\"status\":\"TODO\"},{\"id\":3,\"title\":\"relax\",\"description\":\"watch a good netflix show\",\"createdTime\":\"2024-03-13T17:15:03\",\"status\":\"TODO\"}]";
    private static String jsonUpdateStatus = "{\"id\":2,\"title\":\"laundry\",\"description\":\"wash the kids pile\",\"createdTime\":\"2024-03-13T17:15:03\",\"status\":\"IN_PROCESS\"}";
    private static String jsonFirstToDo = "{\"id\":1,\"title\":\"workout\",\"description\":\"go to pilates\",\"createdTime\":\"2024-03-13T17:15:03\",\"status\":\"TODO\"}";


    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TodoService toDoService;

    @BeforeEach
    void setUp() throws InvalidIdException {
        List<ToDo> todos = createTestData();
        when(toDoService.save(any())).thenReturn(todos.stream().findFirst().get());
        when(toDoService.getAllToDos()).thenReturn(todos);
        when(toDoService.findToDoById(anyLong())).thenReturn(todos.stream().filter(t -> t.getId()==1).findAny().orElse(null));
        when(toDoService.updateStatus(2, TodoStatusEnum.IN_PROCESS)).thenReturn(updatedEntity());
        when(toDoService.updateEntity(any())).thenReturn(updatedEntity());

    }

    @Test
    public void createToDoTest() throws Exception {
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/todos-api/create")
                        .content(jsonFirstToDo)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.content().string(jsonFirstToDo))
                .andReturn();

        assertNotNull(result);
        assertEquals(jsonFirstToDo, result.getResponse().getContentAsString());
    }

    @Test
    public void getAllToDosTest() throws Exception {
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/todos-api/all"))
                .andExpect(MockMvcResultMatchers.content().string(jsonAllToDos))
                .andReturn();

        assertNotNull(result);
        assertEquals(jsonAllToDos, result.getResponse().getContentAsString());
    }

    @Test
    public void geToDoByIdTest() throws Exception {
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/todos-api/id/1"))
                .andExpect(MockMvcResultMatchers.content().string(jsonFirstToDo))
                .andReturn();

        assertNotNull(result);
        assertEquals(jsonFirstToDo, result.getResponse().getContentAsString());
    }

    @Test
    public void deleteToDoTest() throws Exception {
      mockMvc.perform(MockMvcRequestBuilders.put("/todos-api/delete/1")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();
    }

    @Test
    public void updateEntity() throws Exception {
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.put("/todos-api/update")
                        .content(jsonUpdateStatus)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.content().string(jsonUpdateStatus))
                .andReturn();

        assertNotNull(result);
        assertEquals(jsonUpdateStatus, result.getResponse().getContentAsString());
    }

    @Test
    public void updateStatus() throws Exception {
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.put("/todos-api/update/status/2/IN_PROCESS"))
                .andExpect(MockMvcResultMatchers.content().string(jsonUpdateStatus))
                .andReturn();

        assertNotNull(result);
        assertEquals(jsonUpdateStatus, result.getResponse().getContentAsString());
    }

    private static List<ToDo> createTestData() {
        return List.of(
                ToDo.builder()
                        .id(1)
                        .title("workout")
                        .description("go to pilates")
                        .createdTime(LocalDateTime.of(2024,03,13,17,15,03))
                        .status(TodoStatusEnum.TODO)
                        .build(),
                ToDo.builder()
                        .id(2)
                        .title("laundry")
                        .description("wash the kids pile")
                        .createdTime(LocalDateTime.of(2024,03,13,17,15,03))
                        .status(TodoStatusEnum.TODO)
                        .build(),
                ToDo.builder()
                        .id(3)
                        .title("relax")
                        .description("watch a good netflix show")
                        .createdTime(LocalDateTime.of(2024,03,13,17,15,03))
                        .status(TodoStatusEnum.TODO)
                        .build()
        );
    }

    private static ToDo updatedEntity() {
        return ToDo.builder()
                .id(2)
                .title("laundry")
                .description("wash the kids pile")
                .createdTime(LocalDateTime.of(2024,03,13,17,15,03))
                .status(TodoStatusEnum.IN_PROCESS)
                .build();
    }
}
