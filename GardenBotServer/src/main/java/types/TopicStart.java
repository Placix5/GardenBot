package types;

public class TopicStart {

	private Integer idBot;

	public TopicStart() {
		super();
	}

	public TopicStart(Integer idBot) {
		super();
		this.idBot = idBot;
	}

	public Integer getIdBot() {
		return idBot;
	}

	public void setIdBot(Integer idBot) {
		this.idBot = idBot;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idBot == null) ? 0 : idBot.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TopicStart other = (TopicStart) obj;
		if (idBot == null) {
			if (other.idBot != null)
				return false;
		} else if (!idBot.equals(other.idBot))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "TopicStart [idBot=" + idBot + "]";
	}
	
	
	
}
