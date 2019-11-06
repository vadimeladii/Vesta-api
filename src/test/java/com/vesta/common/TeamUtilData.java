package com.vesta.common;

import com.vesta.controller.view.TeamView;
import com.vesta.repository.entity.TeamEntity;
import com.vesta.service.dto.TeamDto;
import org.apache.commons.lang3.RandomStringUtils;

public class TeamUtilData {

    public static Long TEAM_ID = Long.parseLong(RandomStringUtils.randomNumeric(10));
    public static String FIRST_NAME = RandomStringUtils.randomAlphabetic(24);
    public static String LAST_NAME = RandomStringUtils.randomAlphabetic(20);
    public static String TEAM_NAME = RandomStringUtils.randomAlphabetic(10);
    public static String OFFICE_NAME = RandomStringUtils.randomAlphabetic(9);
    public static String PHONE_NUMBER = RandomStringUtils.randomNumeric(9);
    public static String MOBILE_PHONE = RandomStringUtils.randomNumeric(19);
    public static String OFFICE_KEYS = RandomStringUtils.randomNumeric(3);
    public static String WORK_SINCE = RandomStringUtils.randomNumeric(10);
    public static String BIRTH_DAY = RandomStringUtils.randomNumeric(10);

    public static TeamEntity teamEntity() {

        TeamEntity teamEntity = new TeamEntity();
        teamEntity.setId(TEAM_ID);
        teamEntity.setFirstName(FIRST_NAME);
        teamEntity.setLastName(LAST_NAME);
        teamEntity.setProjectName(TEAM_NAME);
        teamEntity.setOffice(OFFICE_NAME);
        teamEntity.setPhone(PHONE_NUMBER);
        teamEntity.setMobilePhone(MOBILE_PHONE);
        teamEntity.setOffice(OFFICE_NAME);
        teamEntity.setHasOfficeKeys(OFFICE_KEYS);
        teamEntity.setWorkAtPentalogSince(WORK_SINCE);
        teamEntity.setDateOfBirth(BIRTH_DAY);

        return teamEntity;
    }

    public static TeamDto teamDto (){
        TeamDto teamDto = new TeamDto();

        teamDto.setId(TEAM_ID);
        teamDto.setFirstName(FIRST_NAME);
        teamDto.setLastName(LAST_NAME);
        teamDto.setProjectName(TEAM_NAME);
        teamDto.setOffice(OFFICE_NAME);
        teamDto.setPhone(PHONE_NUMBER);
        teamDto.setMobilePhone(MOBILE_PHONE);
        teamDto.setHasOfficeKeys(OFFICE_KEYS);
        teamDto.setWorkAtPentalogSince(WORK_SINCE);
        teamDto.setDateOfBirth(BIRTH_DAY);

        return teamDto;
    }

    public static TeamView teamView(){
        TeamView teamView = new TeamView();

        teamView.setId(TEAM_ID);
        teamView.setFirstName(FIRST_NAME);
        teamView.setLastName(LAST_NAME);
        teamView.setProjectName(TEAM_NAME);
        teamView.setOffice(OFFICE_NAME);
        teamView.setPhone(PHONE_NUMBER);
        teamView.setMobilePhone(MOBILE_PHONE);
        teamView.setHasOfficeKeys(OFFICE_KEYS);
        teamView.setWorkAtPentalogSince(WORK_SINCE);
        teamView.setDateOfBirth(BIRTH_DAY);

        return teamView;
    }
}
