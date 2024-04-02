import org.junit.Test;
import zhizhu.*;

import java.util.LinkedList;
import java.util.List;

public class Test_iscanopen {
    @Test
    public void test1(){//�ƶ��ķ����ĺ���6
        List<Card> list=new LinkedList<Card>();
        list.add(new Card(Const.HEITAO,6));
        for(int i=0;i<list.size();i++){
            list.get(i).setShow(true);
        }
        Cache.moveCards=list;
        assert GameTool.isCanMove()==true;
    }
    @Test
    public void test2(){//�ƶ����ƶ�����ϵ��������Ƿ����ĺ���Q�������ĺ���A
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
    public void test3(){//�ƶ����ƶ�����ϵ��������Ƿ�����÷��Q�������ĺ���J
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
    public void test4(){//�ƶ����ƶ�����ϵ���������δ���ĺ���Q�������ĺ���J
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
    public void test5(){//�ƶ����ƶ�����ϵ��������Ƿ����ĺ���2�������ĺ���A
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
