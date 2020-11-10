
package scoreservice;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;



@RestController
public class ScoreController {
    
    @Autowired
    private ScoreRepository sr;
    
    @Autowired
    private GameRepository gr;
    
    @PostMapping("/games/{name}/scores")
    public Score createScores(@RequestBody Score score, @PathVariable String name){
       Game game = gr.findByName(name);
       score.setGame(game);
       return sr.save(score);
    }
    
    @GetMapping("games/{name}/scores")
    public List <Score> all(@PathVariable String name){
        Game game = gr.findByName(name);
        return sr.findByGame(game);
    }
    
    @GetMapping("/games/{name}/scores/{id}")
    public Score getScore(@PathVariable Long id, @PathVariable String name){
        Game game = gr.findByName(name);
        return sr.findByGameAndId(game, id);
    }
    
    @DeleteMapping("/games/{name}/scores/{id}")
    public Score delete(@PathVariable Long id, @PathVariable String name){
        Game game = gr.findByName(name);
        Score score = sr.findByGameAndId(game, id);
        sr.deleteById(id);
        return score;
        
    }
    
}
