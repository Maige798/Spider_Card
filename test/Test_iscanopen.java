import org.junit.Test;
import zhizhu.*;

import java.util.LinkedList;
import java.util.List;

public class Test_iscanopen {
    @Test
    public void test1(){//移动的翻开的黑桃6
        List<Card> list=new LinkedList<Card>();
        list.add(new Card(Const.HEITAO,6));
        for(int i=0;i<list.size();i++){
            list.get(i).setShow(true);
        }
        Cache.moveCards=list;
        assert GameTool.isCanMove()==true;
    }
    @Test
    public void test2(){//移动的牌堆里从上到下依次是翻开的黑桃Q，翻开的黑桃A
        List<Card> list=new LinkedList<Card>();
        list.add(new Card(Const.HEITAO,12));
        list.add(new Card(Const.HEITAO,1));
        for(int i=0;i<list.size();i++){
            list.get(i).setShow(true);
        }
        Cache.moveCards=list;
        assert GameTool.isCanMove()==false;
    }
    @Test
    public void test3(){//移动的牌堆里从上到下依次是翻开的梅花Q，翻开的黑桃J
        List<Card> list=new LinkedList<Card>();
        list.add(new Card(Const.CAOHUA,12));
        list.add(new Card(Const.HEITAO,11));
        for(int i=0;i<list.size();i++){
            list.get(i).setShow(true);
        }
        Cache.moveCards=list;
        assert GameTool.isCanMove()==false;
    }
    @Test
    public void test4(){//移动的牌堆里从上到下依次是未翻的黑桃Q，翻开的黑桃J
        List<Card> list=new LinkedList<Card>();
        list.add(new Card(Const.CAOHUA,12));
        list.add(new Card(Const.HEITAO,11));
        for(int i=0;i<list.size();i++){
            list.get(i).setShow(true);
        }
        list.get(0).setShow(false);
        Cache.moveCards=list;
        assert GameTool.isCanMove()==false;
    }
    @Test
    public void test5(){//移动的牌堆里从上到下依次是翻开的黑桃2，翻开的黑桃A
        List<Card> list=new LinkedList<Card>();
        list.add(new Card(Const.HEITAO,2));
        list.add(new Card(Const.HEITAO,1));
        for(int i=0;i<list.size();i++){
            list.get(i).setShow(true);
        }
        Cache.moveCards=list;
        assert GameTool.isCanMove()==true;
    }
}
