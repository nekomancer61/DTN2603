import java.util.Date;

public class GroupAccount {
    private int groupId;
    private int accountId;
    private Date createDate;
    public GroupAccount() {
    }
    public GroupAccount(int groupId, int accountId, Date createDate) {
        this.groupId = groupId;
        this.accountId = accountId;
        this.createDate = createDate;
    }
    public int getGroupId() {
        return groupId;
    }
    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }
    public int getAccountId() {
        return accountId;
    }
    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }
    public Date getCreateDate() {
        return createDate;
    }
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
    @Override
    public String toString() {
        return "GroupAccount [groupId=" + groupId + ", accountId=" + accountId + ", createDate=" + createDate + "]";
    }
    
    
}
