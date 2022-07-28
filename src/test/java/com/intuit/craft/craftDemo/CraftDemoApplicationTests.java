package com.intuit.craft.craftDemo;

import com.intuit.craft.craftDemo.entity.Game;
import com.intuit.craft.craftDemo.repository.GameRepository;
import com.intuit.craft.craftDemo.service.GameServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

@SpringBootTest
@RunWith(SpringRunner.class)
class CraftDemoApplicationTests {

	@Test
	void contextLoads() {
	}

	public static final int topKCount = 2;


	@InjectMocks
	GameServiceImpl gameService;

	@Mock
	GameRepository gameRepository;

	@Test
	public void testGetTopPlayer(){
		try{
			when(gameRepository.findTopKScores(topKCount)).thenReturn(getGames());
			List<Game> list = gameService.getTopKScores();
			assertNotNull(list);
			assertEquals(topKCount,list.size());
			assertEquals(list.get(0).getScore(), 200);
			assertEquals(list.get(0).getUserId(), "u1");

		}
		catch(Exception e){
			System.out.println(e.getMessage());
		}
	}

	private List<Game> getGames()
	{
		List<Game> ps = new ArrayList<>();

		Game g1 = new Game("game1", 200);
		g1.setUserId("u1");
		g1.setCreatedAt(new Date((long)20220706));
		ps.add(g1);

		Game g2 = new Game("game2", 100);
		g2.setUserId("u2");
		g2.setCreatedAt(new Date((long)20220706));
		ps.add(g2);



		return ps;
	}

}
