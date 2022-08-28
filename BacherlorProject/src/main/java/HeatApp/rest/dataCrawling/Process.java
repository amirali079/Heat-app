package HeatApp.rest.dataCrawling;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


import java.io.*;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

@Service
public class Process {

    @Autowired
    private RestTemplate restTemplate;

   //@PostConstruct
    public void process() throws InterruptedException, IOException {

      // f403();
      //  readIds();
      //  readData();

    }

    void readData() throws InterruptedException {
        System.out.println("================read data=================");
        Set<String> ids = new HashSet<>();
        try {
            Scanner sc = new Scanner(new File("ids9.txt"));

            for (int i = 1; i <=100 ; i++)
                ids.add(sc.nextLine());
            sc.close();
            System.out.println(ids.size());
            System.out.println(ids.toString());

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        Set<String> data = new HashSet<>();
        Gson gson = new Gson();

        Integer ind = 1;
        for (String id : ids) {


            ResponseEntity response = RestService.getData(restTemplate,id,"ebeca2bb544d48c9ba61d8f0f73425ef");

            System.out.println(response.toString());
            JsonElement element = gson.fromJson(response.getBody().toString(), JsonElement.class);

            System.out.println(ind+++" ++ "+element.toString());
                data.add(element.toString());

                Thread.sleep(1100);
        }

        try {
            Integer baseId = 801;
            FileWriter ft ;
            FileWriter fj ;

            for (String d:data) {

               fj = new FileWriter(new File("./data/json/d"+baseId+".json"));
               ft = new FileWriter(new File("./data/txt/d"+baseId+".txt"));
//               fj = new FileWriter(new File("./data/d"+baseId+".json"));
//               ft = new FileWriter(new File("./data/d"+baseId+".txt"));
               fj.write(d);
               ft.write(d);
               baseId++;
               fj.flush();
               ft.flush();
            }
          //  for (JsonElement )


        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("_____________________end_______________________");
    }


    void readIds(){
       System.out.println("===================================");
//       Set<String> ids = new HashSet<>();
//       Gson gson = new Gson();
//
//
//       for (int i = 1; i < 12; i++) {
//           ResponseEntity response = RestService.get(restTemplate,1000,i*100);
//
//           System.out.println(response.toString());
//           JsonElement element = gson.fromJson(response.getBody().toString(), JsonElement.class);
//           JsonArray results = (JsonArray) element.getAsJsonObject().get("results");
//
//           for (JsonElement object:results)
//               ids.add(object.getAsJsonObject().get("id").getAsString());
//           System.out.println(ids.size());
//       }
//
//
//       System.out.println(ids.toString());

       try {
           Scanner sc = new Scanner(new File("ids.txt"));
           Set<String> readId = new HashSet<>();

           for (int i = 0; i <900 ; i++)
               readId.add(sc.nextLine());

           System.out.println(readId.size()+" --- "+readId.toString());

           int  index = 0;
           int indexFile = 1;
           FileWriter f =  new FileWriter(new File("ids"+indexFile+".txt"));
           for (String id : readId) {
               if (index == 100){
                   f.flush();
                   f.close();

                   index=0;
                   indexFile++;

                   f =  new FileWriter(new File("ids"+indexFile+".txt"));
               }
               f.write(id+"\n");
               index++;
           }

           System.out.println("__________end_________");
       } catch (IOException e) {
           e.printStackTrace();
       }
    }

    void f403() throws IOException {
        Gson gson = new Gson();
        ResponseEntity response = RestService.getData(restTemplate,"657598","ebeca2bb544d48c9ba61d8f0f73425ef");

        System.out.println(response.toString());
        JsonElement element = gson.fromJson(response.getBody().toString(), JsonElement.class);

        FileWriter ft ;
        FileWriter fj ;


        fj = new FileWriter(new File("./data/json/d403.json"));
        ft = new FileWriter(new File("./data/txt/d403.txt"));
//               fj = new FileWriter(new File("./data/d"+baseId+".json"));
//               ft = new FileWriter(new File("./data/d"+baseId+".txt"));
        fj.write(element.toString());
        ft.write(element.toString());
        fj.flush();
        ft.flush();
    }





}

