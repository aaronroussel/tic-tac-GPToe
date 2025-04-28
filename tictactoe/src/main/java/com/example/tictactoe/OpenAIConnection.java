package com.example.tictactoe;

import com.openai.client.OpenAIClient;
import com.openai.client.okhttp.OpenAIOkHttpClient;
import com.openai.models.chat.completions.ChatCompletion;
import com.openai.models.chat.completions.ChatCompletionCreateParams;
import com.openai.models.ChatModel;

public class OpenAIConnection {
  private static final OpenAIClient client;

  static {
    String apiKey = "";
    String baseUrl = "";
    String orgId = "";
    String projectId = "";

    OpenAIOkHttpClient.Builder optionsBuilder = OpenAIOkHttpClient.builder()
      .apiKey(apiKey)
      .baseUrl(baseUrl)
      .organization(orgId)
      .project(projectId);
    client  = optionsBuilder.build(); 
  }

  private OpenAIConnection() {}

  public static String getResponse(String board) {

    ChatCompletionCreateParams params = ChatCompletionCreateParams.builder()
      .addDeveloperMessage("You are playing a game of tic tac toe. I will provide an array that represents the game board. You are playing as the letter O. Respond with the index of where you would like to place your next move. ONLY RESPOND WITH THE INDEX OF THE ARRAY FOR WHERE YOU WANT TO PLACE YOUR MOVE. DO NOT RESPOND WITH ANYTHING OTHER THAN A SINGLE NUMBER. Here is the current game board: " + board)      
      .model(ChatModel.GPT_4_1_NANO)
      .build();

    ChatCompletion future = client.chat().completions().create(params);

    return future.choices().get(0).message().content().get();
  }
 
}
