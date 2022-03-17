package hello.servlet.domain.member;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 동시성 문제가 고려되어 있지 않음, 실무에서는 ConcurrentHashMap, AtomicLong 사용 고려하여 사용하도록 한다.
 */
public class MemberRepository {

    //key는 id 값은 member로 저장할 것임
    private static Map<Long, Member> store = new HashMap<>(); //static 사용
    private static long sequence = 0L; //static 사용
    private static final MemberRepository instance = new MemberRepository();

    public static MemberRepository getInstance() {
        return instance; //생성하여 사용하지 않고 이것을 이용하여 저장소를 사용하도록 한다.
    }

    private MemberRepository() {
    } //아무나 저장소를 생성하지 못하게 막아줘야 한다.

    public Member save(Member member) { //저장
        member.setId(++sequence);
        store.put(member.getId(), member);
        return member;
    }

    public Member findById(Long id) {
        return store.get(id);
    }

    public List<Member> findAll() {
        return new ArrayList<>(store.values());
    }

    public void clearStore() {
        store.clear();
    }
}
