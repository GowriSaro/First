package webproject.model;

public class Student {
	
	private int rollNo;
	private String firstName;
	private String lastName;
	private String dob;
	private String email;
	private String gender;
	private String skills;
	private long contactNo;
	private String address;
	private String branch;
	private Object document;
	private String docType;
	private Object image;
	private Object audio;
	private Object video;
	
	public Student() {
		super();
	}
	
	public Student(String firstName, String lastName, String dob, String email, String gender, String skills,
			long contactNo, String address, String branch) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.dob = dob;
		this.email = email;
		this.gender = gender;
		this.skills = skills;
		this.contactNo = contactNo;
		this.address = address;
		this.branch = branch;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getDob() {
		return dob;
	}

	public void setDob(String dob) {
		this.dob = dob;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getSkills() {
		return skills;
	}

	public void setSkills(String skills) {
		this.skills = skills;
	}

	public long getContactNo() {
		return contactNo;
	}

	public void setContactNo(long contactNo) {
		this.contactNo = contactNo;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getBranch() {
		return branch;
	}

	public void setBranch(String branch) {
		this.branch = branch;
	}

	public int getRollNo() {
		return rollNo;
	}

	public void setRollNo(int rollNo) {
		this.rollNo = rollNo;
	}
	
	public Object getDocument() {
		return document;
	}

	public void setDocument(Object document) {
		this.document = document;
	}

	public Object getImage() {
		return image;
	}

	public void setImage(Object image) {
		this.image = image;
	}

	public Object getAudio() {
		return audio;
	}

	public void setAudio(Object audio) {
		this.audio = audio;
	}

	public Object getVideo() {
		return video;
	}

	public void setVideo(Object video) {
		this.video = video;
	}

	public String getDocType() {
		return docType;
	}

	public void setDocType(String docType) {
		this.docType = docType;
	}
}
