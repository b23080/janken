package oit.is.z3183.kaizi.janken.model;

public class Janken {
  private String myHand;
  private String cpuHand;
  private String result;

  public Janken(String myHand, String cpuHand) {
    this.myHand = myHand;
    this.cpuHand = cpuHand;
    judge();
  }

  private void judge() {
    if (myHand.equals(cpuHand)) {
      result = "あいこ";
    } else if ((myHand.equals("グー") && cpuHand.equals("チョキ")) || (myHand.equals("チョキ") && cpuHand.equals("パー"))
        || (myHand.equals("パー") && cpuHand.equals("グー"))) {
      result = "勝ち";
    } else {
      result = "負け";
    }
  }

  public String getMyHand() {
    return myHand;
  }

  public String getCpuHand() {
    return cpuHand;
  }

  public String getResult() {
    return result;
  }
}
