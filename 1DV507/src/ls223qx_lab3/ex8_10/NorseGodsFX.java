package ls223qx_lab3.ex8_10;

import java.util.ArrayList;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class NorseGodsFX extends Application {

	@Override
	public void start(Stage pStage) throws Exception {
		ArrayList<NorseGod> gods = new ArrayList<>(); // Add gods to arrayList, then add the gods to the listView.
		ListView<String> godList = new ListView<>();
		// It probably makes more sense to create the gods arraylist inside the main function, and then pass it via the launch(arg) method, but this works as of right now.

		gods.add(new NorseGod("Loki", "Shape Shifter",
				"Loki (Old Norse: [ˈloki], Modern Icelandic: [ˈlɔːkɪ], often Anglicized as /ˈloʊki/) is a god in Norse mythology. Loki is in some sources the son of Fárbauti and Laufey, and the brother of Helblindi and Býleistr. By the jötunn Angrboða, Loki is the father of Hel, the wolf Fenrir, and the world serpent Jörmungandr. By his wife Sigyn, Loki is the father of Narfi and/or Nari. By the stallion Svaðilfari, Loki is the mother—giving birth in the form of a mare—to the eight-legged horse Sleipnir. In addition, Loki is referred to as the father of Váli in Prose Edda, though this source also refers to Odin as the father of Váli twice, and Váli is found mentioned as a Son of Loki only once."));
		gods.add(new NorseGod("Balder", "'God'",
				"Baldr (also Balder, Baldur) is the god of light, joy, purity, and the summer sun in Norse mythology, and a son of the god Odin and the goddess Frigg. He is the father of Forseti, and He has numerous brothers, such as Thor and Váli.\r\n"
						+ "\r\n"
						+ "In the 12th century, Danish accounts by Saxo Grammaticus and other Danish Latin chroniclers recorded a euhemerized account of his story. Compiled in Iceland in the 13th century, but based on much older Old Norse poetry, the Poetic Edda and the Prose Edda contain numerous references to the death of Baldr as both a great tragedy to the Æsir and a harbinger of Ragnarök. In addition to being loved by all gods and more physical beings, he was so handsome, generous, and good that he gave off light simply by the purity of his character.\r\n"
						+ "\r\n"
						+ "According to Gylfaginning, a book of Snorri Sturluson's Prose Edda, Baldr's wife is Nanna and their son is Forseti. In Gylfaginning, Snorri relates that Baldr had the greatest ship ever built, Hringhorni, and that there is no place more beautiful than his hall, Breidablik."));
		gods.add(new NorseGod("Bragi", "Skald",
				"Bragi (pronounced “BRAG-ee;” Old Norse Bragi, “Poet”) is the wise and learned bard (Old Norse þulr, pronounced “THOOL-ur”) of Valhalla, the magnificent hall of the god Odin. Old Norse poetry from the Viking Age frequently features him regaling the einherjar, the dead who dwell in Valhalla, and welcoming recently deceased heroes into their midst.[1] One Eddic poem depicts him as having runes carved on his tongue.[2]\r\n"
						+ "\r\n"
						+ "Bragi was originally the historical ninth-century bard Bragi Boddason. His poems were so outstandingly artful and moving that subsequent generations imagined that, upon his death, Odin had appointed him the court poet of Valhalla. After all, a troop of elite warriors, kings, and others favored by Odin needed an elite bard to sing of their countless exploits.[3]\r\n"
						+ "\r\n"
						+ "The Old Norse writers of the Christian Middle Ages took this a step further and portrayed Bragi as having been nothing less than a god of poetry. One such author even claimed that one of the Old Norse words for “poetry,” bragr, was derived from Bragi’s name.[4] He was said to be the husband of the goddess Idun, whose fruits guarantee the continued immortality of the gods.\r\n"
						+ "\r\n"
						+ "However, this seems to have been a misunderstanding on the part of such late authors, and there’s no evidence that Bragi was ever actually worshiped as a god while the pre-Christian Norse religion was still a living tradition. [5][6]"));
		gods.add(new NorseGod("Freyr", "I think", "you get the point")); // I think you get the structure after this , syntax is NAME,
													// RACE/TITLE, DESCRIPTION
		// Descriptions stolen from various websites, mostly wikipedia.
		// One of the descriptions should be long enough to demonstrate the scroll functionality of the scroll pane.
		gods.add(new NorseGod("Hel", "Placeholder", "Placeholder"));
		gods.add(new NorseGod("Odin", "Placeholder", "Placeholder"));
		gods.add(new NorseGod("Thor", "Placeholder", "Placeholder"));
		gods.add(new NorseGod("Tyr", "Placeholder", "Placeholder"));

		godList.setMaxSize(150, 400); // Set max size in program, which appears to be actual size.
		
		for (int i = 0; i < 8; i++) {
			godList.getItems().add(gods.get(i).getName()); // Add all god names to the list view
		}

		ScrollPane scroll = new ScrollPane();
		scroll.setMinSize(300, 400); // Create new scroll pane
		scroll.setMaxSize(300, 400);

		final Text name = new Text(""); // Create placeholder texts which will later be changed in the listview event
		final Text race = new Text("");
		final Text content = new Text("");
		name.setFont(new Font(15));
		race.setFont(new Font(10));
		content.setFont(new Font(12));
		content.setWrappingWidth(280); // Changing of the text, pushing some text into 'new lines' when width is
										// exceeded.

		VBox textHold = new VBox(); // VBox holds text in a neat fashion.
		textHold.getChildren().addAll(name, race, content);
		scroll.setContent(textHold); // Put VBox inside the scroll pane.

		godList.getSelectionModel().selectedIndexProperty().addListener(listen -> // On selection of an item in the list view.
			{
				NorseGod n = gods.get(godList.getSelectionModel().getSelectedIndex()); // Get the NorseGod object from the arraylist.
				name.setText(n.getName()); // Change the labels content.
				race.setText(n.getRace());
				content.setText(n.getDesc());
			});

		Text topText = new Text("Norse Gods and other Beings");
		topText.setFont(new Font(15));
		BorderPane.setAlignment(topText, Pos.TOP_LEFT); // Alignment of all elements
		BorderPane.setAlignment(godList, Pos.CENTER_LEFT);
		BorderPane.setAlignment(scroll, Pos.CENTER_RIGHT);
		BorderPane root = new BorderPane(godList, topText, scroll, null, null); // For some reason, a border pane needs either 5 or 1 element(s). Null is accepted as an element for this constructor.

		root.setPrefSize(500, 400); // Set window size.

		pStage.setScene(new Scene(root));
		pStage.setTitle("Norse gods");
		pStage.setResizable(false);
		pStage.show();

	}

	public static void main(String[] args) {
		launch();
	}

}
