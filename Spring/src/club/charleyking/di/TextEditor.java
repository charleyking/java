package club.charleyking.di;

public class TextEditor {
	SpellChecker spellChecker;
	public TextEditor(SpellChecker spellChecker) {
		this.spellChecker = spellChecker;
	}
	
	public void check() {
		System.out.println("in text editor");
		spellChecker.checkSpell();
	}
}
