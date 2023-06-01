
public abstract class Person {
	/* Nombre completo de la persona*/
	protected String name;
	/* Mail de la persona*/
	protected String mail;
	
	
	public Person() {
		
	}
	
	public Person(String name,String mail) {
		setName(name);
		setMail(mail);
	}

	
	public abstract void load(String id);
	
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}
	
	
}