package edu.eci.arsw.threads;

import edu.eci.arsw.blacklistvalidator.HostBlackListsValidator;
import edu.eci.arsw.spamkeywordsdatasource.HostBlacklistsDataSourceFacade;

import java.util.LinkedList;

public class BlackListThreadValidator extends Thread {
    private final String ipAddress;
    private final HostBlacklistsDataSourceFacade skds;
    private static final int BLACK_LIST_ALARM_COUNT=5;
    private int checkedListCount;
    private final int firstServer,lastServer;
    private int ocurrencesCount;
    private final LinkedList<Integer> blackListOcurrences;

    public BlackListThreadValidator(String ipAddress,int firstServer, int lastServer, HostBlacklistsDataSourceFacade skds) {
        this.ipAddress = ipAddress;
        this.skds = skds;
        this.firstServer = firstServer;
        this.lastServer = lastServer;
        ocurrencesCount = 0;
        checkedListCount = 0;
        blackListOcurrences = new LinkedList<>();
    }

    public void run(){
        for(int i=firstServer; i<lastServer && ocurrencesCount<BLACK_LIST_ALARM_COUNT;i++){
            if(skds.isInBlackListServer(i, ipAddress)){
                blackListOcurrences.add(i);
                ocurrencesCount++;
            } else {
                checkedListCount++;
            }
        }
    }

    public LinkedList<Integer> getServers(){
        return blackListOcurrences;
    }

    public int getOcurrencesCount(){
        return ocurrencesCount;
    }

    public int getCheckedListCount() {
        return checkedListCount;
    }
}
