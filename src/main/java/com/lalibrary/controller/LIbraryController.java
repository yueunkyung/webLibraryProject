package com.lalibrary.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.lalibrary.dto.BookVO;
import com.lalibrary.dto.BorrowBookVO;
import com.lalibrary.dto.LibraryVO;
import com.lalibrary.dto.LostAndFoundVO;
import com.lalibrary.dto.UserVO;
import com.lalibrary.model.LibraryService;
import com.lalibrary.view.LibraryView;

public class LIbraryController {
	public static void main(String[] args){
		Scanner sc = new Scanner(System.in);
		LibraryService service = new LibraryService();
		boolean isRun = true;
		
		while(isRun) {
			menu();
			int job = sc.nextInt();
			switch (job) {
			case 1: {
				//통합도서검색-도서 이름으로 검색
				System.out.print("도서 이름>>");
				sc.nextLine();
				String title = sc.nextLine();
				List<BookVO> booklist = service.selectByBookTitle(title);
				if(booklist.isEmpty()) {
					//도서 주문
					isRun = orderBook(sc, service);
				} else {
					LibraryView.print(booklist);
					//도서 대여
					isRun = borrowBook(sc, service);
				}
				break;
			}				
			case 2: {
				//도서 조회 후 대여
				if(inquiryBook(sc, service)) {
					System.out.println("1.처음으로 2.종료");
					int choiceNum = sc.nextInt();
					if(choiceNum == 2) {
						isRun = false;
					}
					break; //case2 break
				}
				isRun = borrowBook(sc, service);
				break; //case2 break
			}
			case 3: {
				//회원 조회
				isRun = showUser(sc, service);
				break;
			}
			case 4: {
				//회원 가입
				isRun = singUp(sc, service);
				break;
			}
			case 5: {
				//분실물 보관센터
				isRun = lostAndFound(sc, service);
				break;
			}
			case 6:
				//프로그램 종료
				isRun = false;
				break;
			default:
				LibraryView.print("번호를 다시 선택하세요.");
				break;
			}
		}
		System.out.println("프로그램 종료");
		sc.close();
	}


	//메뉴리스트
	public static void menu() {
		System.out.println("1.통합 도서 검색");
		System.out.println("2.도서 조회");
		System.out.println("3.회원 검색");
		System.out.println("4.회원 가입");
		System.out.println("5.분실물 보관센터");
		System.out.println("6.종료");
		System.out.print("작업선택>>");
	}

	//분실물 수령
	private static void deleteLostItem(Scanner sc, LibraryService service) {		
		System.out.println("본인의 분실물이 있다면, 수령하시겠습니까?");
		System.out.println("1.네, 수령하겠습니다. 2.아니요, 제가 잃어버린 물건이 없네요.");
		System.out.print("작업선택>>");
		int choiceNum = sc.nextInt();
		if(choiceNum == 1) {
			System.out.println("분실물 아이디를 작성하세요.");
			System.out.print("lost_id>>");
			String itemId = sc.next();

			LostAndFoundVO itemInfo = new LostAndFoundVO(itemId);
			int count = service.deleteLostItem(itemInfo);
			
			if(count>0) {
				LibraryView.print("분실물을 수령하였습니다.");
			}
			else {
				LibraryView.print("분실물 수령이 실패하였습니다. 분실물 정보를 다시 한번 확인하시기 바랍니다.");
			}
		}
	}
	
	//분실물 보관센터
	private static boolean lostAndFound(Scanner sc, LibraryService service) {
		boolean isRestart = true;
		System.out.println("분실물 보관센터를 선택하였습니다.");
		System.out.println("1.분실물 찾기 2.분실물 등록");
		System.out.print("작업선택>>");
		
		int choiceNum = sc.nextInt();
		List<LostAndFoundVO> targetList = new ArrayList<>();
		
		switch (choiceNum) {
			case 1: {
				System.out.println("분실물 찾기를 선택하였습니다.");
				System.out.println("1.분실물 전체 조회 2.분실물 검색");
				System.out.print("작업선택>>");
				int show = sc.nextInt();
				if(show == 1) {
					System.out.println("분실물 전체 조회를 선택하였습니다.");
					targetList = service.selectLostAndFound("all", null);
//					LibraryView.print(targetList);
				} else if(show == 2) {
					System.out.println("분실물 검색을 선택하였습니다.");
					System.out.print("분실물 이름>>");
					sc.nextLine();
					String name = sc.nextLine();
					targetList = service.selectLostAndFound("property_name", name);
				}

				if(targetList.size() > 0) {
					LibraryView.print(targetList);
					deleteLostItem(sc, service);
				} else {
					LibraryView.print("찾으시는 분실물이 없습니다. 다시 한번 확인하시기 바랍니다.");												
				}
				
				break;
			}
			case 2: {
				System.out.println("분실물 등록을 선택하였습니다.");
				System.out.println("도서관 아이디를 작성하세요.");
				System.out.print("library_id>>");
				String libId = sc.next();
				sc.nextLine();
				System.out.println("분실물 이름을 작성하세요.");
				System.out.print("분실물 이름>>");
				String name = sc.nextLine();
				System.out.println("발견한 날짜를 작성하세요.");
				System.out.print("YYYY/MM/DD>>");
				String foundDate = sc.next();
				
				LostAndFoundVO itemInfo = new LostAndFoundVO(libId, name, foundDate);
				int count = service.insertLostItem(itemInfo);
				
				if(count>0) {
					LibraryView.print("분실물 등록이 완료되었습니다.");
				}
				else {
					LibraryView.print("분실물 등록을 실패하였습니다. 분실물 정보를 다시 한번 확인하시기 바랍니다.");
				}
				
				break;
			}
		}//END_switch

		System.out.println("1.처음으로 2.종료");
		System.out.print("작업선택>>");
		int nextDo = sc.nextInt();
		if(nextDo==2) {
			isRestart = false;
		}
		return isRestart;
	}
	
	//회원 가입
	private static boolean singUp(Scanner sc, LibraryService service) {
		boolean isRestart = true;
		System.out.println("회원가입을 선택하였습니다.");
		System.out.println("도서관 아이디를 하단 리스트를 참고하여 작성하세요.");
		LibraryView.print(service.selectLibraryAll());
		System.out.print("library_id>>");
		String libId = sc.next();
		System.out.println("성함을 작성하세요.");
		System.out.print("이름>>");
		String name = sc.next();
		System.out.println("사는 지역을 작성하세요.");
		System.out.print("지역 이름>>");
		String loc = sc.next();
		System.out.println("핸드폰 번호를 작성하세요.");
		System.out.print("핸드폰 번호>>");
		String phoneNum = sc.next();
		
		UserVO userInfo = new UserVO(libId, name, loc, phoneNum);
		int count = service.insertSignUp(userInfo);
		if(count>0) {
			LibraryView.print("회원 가입이 완료되었습니다. 오늘부터 1일.");
		}
		else {
			LibraryView.print("회원 가입이 실패하였습니다. 개인 정보를 다시 한번 확인하시기 바랍니다.");
		}
		
		System.out.println("1.처음으로 2.종료");
		if(sc.nextInt() == 2) {
			isRestart = false;
		}
		return isRestart;
	}
	
	//회원 조회
	private static boolean showUser(Scanner sc, LibraryService service) {
		boolean isRestart = true;
		System.out.println("1.모든 회원 조회 2.이름 검색 3.회원 아이디 검색 4.도서관별 회원 전체 조회");
		System.out.print("작업선택>>");
		int choiceNum = sc.nextInt();
		List<UserVO> targetList = new ArrayList<>();
		switch (choiceNum) {
			case 1: {
				System.out.println("모든 회원 조회를 선택하였습니다.");
				targetList = service.selectUser("all", null);
				break;
			}
			case 2: {
				System.out.println("이름으로 회원 검색을 선택하였습니다.");
				System.out.print("이름>>");
				String name = sc.next();
				targetList = service.selectUser("user_name", name);
				break;
			}
			case 3: {
				System.out.println("회원 아이디로 회원 검색을 선택하였습니다.");
				System.out.print("회원 아이디>>");
				String userId = sc.next();
				targetList = service.selectUser("user_id", userId);
				break;
			}
			case 4: {
				System.out.println("도서관별 회원 전체 조회를 선택하였습니다. 도서관 아이디를 작성하세요.");
				LibraryView.print(service.selectLibraryAll());
				System.out.print("도서관 아이디>>");
				String libraryId = sc.next();
				targetList = service.selectUser("library_id", libraryId);
				break;
			}
		}//END_switch
		
		if(targetList.size() > 0) {
			LibraryView.print(targetList);
		} else {
			LibraryView.print("검색된 회원이 없습니다.");			
		}

		System.out.println("1.처음으로 2.종료");
		System.out.print("작업선택>>");
		int nextDo = sc.nextInt();
		if(nextDo==2) {
			isRestart = false;
		}
		return isRestart;
	}
	
	//도서 조회
	private static boolean inquiryBook(Scanner sc, LibraryService service) {
		boolean flag = false;
		System.out.println("1.도서 전제 조회 2.도서관별 도서 전체 조회 3.인기 도서 조회");
		System.out.print("작업선택>>");
		int inquiry = sc.nextInt();
		switch (inquiry) {
			case 1:{
				List<BookVO> all = service.selectBookAll();
				System.out.println("도서 전체 조회를 선택하였습니다.");
				LibraryView.print(all);
				break;	
			}
			case 2:{
				List<LibraryVO> libraryAll = service.selectLibraryAll();
				System.out.println("도서관별 도서를 조회를 선택하였습니다.");
				LibraryView.print(libraryAll);
				System.out.println("도서관별 도서를 조회하기 위해 도서관 아이디를 작성하세요.");
				System.out.print("library_id>>");
				String libId = sc.next();
				List<BookVO> bookAll = service.selectEachLibraryBook(libId);
				if(bookAll.size() > 0) {
					LibraryView.print(bookAll);					
				} else {
					LibraryView.print("등록된 도서가 없습니다.");
					flag = true;
				}
				
				break;
			}
			case 3:{
				int rank = 10;
				System.out.println("인기 도서 몇위까지 조회하시겠습니까?");
				System.out.println("1.10위 2.20위 3.30위 4.40위 5.50위");
				System.out.println("선택>>");
				int rankCnt = sc.nextInt();
				if(rankCnt == 2) {
					rank = 20;
				} else if(rankCnt == 3) {
					rank = 30;					
				} else if(rankCnt == 4) {
					rank = 40;					
				} else if(rankCnt == 5) {
					rank = 50;					
				}
				List<BookVO> PopularBook = service.selectPopularBook(rank);
				System.out.println("인기도서 TOP"+rank+" 조회를 선택하였습니다.");
				LibraryView.print(PopularBook);
				break;
			}			
		}//END_switch
		return flag;
	}
	
	//도서 대여
	public static boolean borrowBook(Scanner sc, LibraryService service) {
		boolean isRestart = true;
		
		System.out.println("도서를 대여 하겠습니까?");
		System.out.println("1.도서 대여 2.처음으로 3.종료");
		System.out.print("작업선택>>");
		int borrowbook = sc.nextInt();
		switch (borrowbook) {
			case 1:{
				// BORROW_ID, BOOK_ID, USER_ID, BORROW_DATE, RETURN_DATE
				// 받을 것 중에 BOOK_ID, USER_ID
				System.out.println("도서 대여를 선택하였습니다. 상세 정보를 작성하세요.");
				System.out.print("book_id>>");
				String bookId = sc.next();				
				System.out.println("당신의 user_id를 작성하세요.");
				System.out.print("user_id>>");
				String userId = sc.next();
				
				BorrowBookVO borrowInfo = new BorrowBookVO(bookId, userId);
				int count = service.insertBorrowBook(borrowInfo);
				if(count>0) {
					LibraryView.print(userId+"님 도서("+bookId+") 대여가 완료되었습니다. 도서반납일은 금일로부터 한달 후 입니다.");
				}
				else {
					LibraryView.print("상세 정보가 잘못되었습니다.");
					isRestart = false;
				}
				
				break;
			}
			case 3: isRestart = false; break;
			default : {
				break;
			}
			
		}//End_switch
		return isRestart;
	}
	
	//도서 주문
	public static boolean orderBook(Scanner sc, LibraryService service) {
		boolean isrun = true;
		
		System.out.println("찾으시는 책이 없나요? 희망 도서를 신청하시겠습니까?");
		System.out.println("1.도서 주문 2.처음으로 3.종료");
		System.out.print("작업선택>>");
		int orderbook = sc.nextInt();
		switch (orderbook) {
			case 1:{
				System.out.println("도서 상세 정보를 작성하세요.");
				System.out.print("도서관ID>>");
				String libraryId = sc.next();
				System.out.print("책Type>>");
				int bookType = sc.nextInt();
				sc.nextLine();
				System.out.print("책이름>>");
				String bookName = sc.nextLine();
				System.out.print("가격>>");
				int price = sc.nextInt();
	
				BookVO writeBookInfo = new BookVO(libraryId, bookType, bookName, price);
				int count = service.insertOrderBook(writeBookInfo);
				if(count>0) {
					LibraryView.print("주문하신 책은 오늘 날짜 기준 3일 후에 입고됩니다.");
				}
				else {
					LibraryView.print("도서 정보가 잘못되었습니다.");
					isrun = false;
				}
				break;
			}
			case 3: isrun = false; break;
			default : {
				break;
			}
		}//End_switch
		return isrun;
	}
}