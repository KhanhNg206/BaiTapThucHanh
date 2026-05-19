package network;

import dto.movieDTO;
import service.impl.showServiceImpl;
import service.showService;

import java.io.DataInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.time.LocalDateTime;

public class ClientHandle implements Runnable{
    private Socket socket;
    private showService service;

    public ClientHandle(Socket socket) {
        this.socket = socket;
        this.service = new showServiceImpl();
    }

    @Override
    public void run() {
        try(
                DataInputStream in = new DataInputStream(socket.getInputStream());
                ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
                ){

            while(true){
                String command =in.readUTF();
                switch(command){
                    case "cauA" -> {
                        String director = in.readUTF();
                        out.writeObject(service.listShowByCurrentDateAndDirector(director));
                        out.flush();
                    }
                    case "cauB" -> {
                        String showId = in.readUTF();
                        out.writeObject(service.updateShowDateTime(showId, LocalDateTime.now().minusDays(5)));
                        out.flush();
                    }
                    case "cauC" ->{
                        movieDTO movie = new movieDTO();
                        movie.setId("M011");
                        movie.setTitle("new title");
                        movie.setDirector("new director");
                        movie.setGenre("new genre");
                        movie.setReleaseYear(2005);
                        movie.setDuration(200);
                        movie.setShows(null);
                        out.writeObject(service.addMovie(movie));
                        String infoMovie = movie.toString();
                        out.writeUTF(infoMovie);
                        out.flush();
                    }
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
