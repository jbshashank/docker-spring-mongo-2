package com.candidjava.spring.bean;

import java.util.List;

public class SkillDetails {
	private List<String> skillset;
	private String SkillRanking;
	
	public List<String> getSkillset() {
		return skillset;
	}
	public void setSkillset(List<String> skillset) {
		this.skillset = skillset;
	}
	public String getSkillRanking() {
		return SkillRanking;
	}
	public void setSkillRanking(String skillRanking) {
		SkillRanking = skillRanking;
	}
	
}
