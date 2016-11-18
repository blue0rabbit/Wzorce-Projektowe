package eu.jpereira.trainings.designpatterns.structural.adapter.thirdparty;

import eu.jpereira.trainings.designpatterns.structural.adapter.exceptions.CodeMismatchException;
import eu.jpereira.trainings.designpatterns.structural.adapter.exceptions.IncorrectDoorCodeException;
import eu.jpereira.trainings.designpatterns.structural.adapter.model.Door;
import eu.jpereira.trainings.designpatterns.structural.adapter.thirdparty.exceptions.CannotChangeCodeForUnlockedDoor;
import eu.jpereira.trainings.designpatterns.structural.adapter.thirdparty.exceptions.CannotChangeStateOfLockedDoor;
import eu.jpereira.trainings.designpatterns.structural.adapter.thirdparty.exceptions.CannotUnlockDoorException;

public class ThirdPartyDoorAdapter extends ThirdPartyDoor implements Door {

	@Override
	public void open(String code) throws IncorrectDoorCodeException {
		 try {
			this.unlock(code);
			try {
				this.setState(DoorState.OPEN);
			} catch (CannotChangeStateOfLockedDoor e) {
				throw new IncorrectDoorCodeException();
			}
		} catch (CannotUnlockDoorException e) {
			throw new IncorrectDoorCodeException();
		}
	}

	@Override
	public void close() {
		//mozna zamienic lock i setstate miejscami i tak bez roznic... gdzie tu logika
		try {
			this.setState(DoorState.CLOSED);
		} catch (CannotChangeStateOfLockedDoor e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.lock();

	}

	@Override
	public boolean isOpen() {
		if(this.getLockStatus() == LockStatus.UNLOCKED && this.getState() == DoorState.OPEN)
			return true;
		else
			return false;
	}

	@Override
	public void changeCode(String oldCode, String newCode,
			String newCodeConfirmation) throws IncorrectDoorCodeException,
			CodeMismatchException {
		if(newCode.equals(newCodeConfirmation))
		{
			this.open(oldCode);
			try {
				this.setNewLockCode(newCodeConfirmation);
			} catch (CannotChangeCodeForUnlockedDoor e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else
		{
			throw new CodeMismatchException();
		}
		

	}

	@Override
	public boolean testCode(String code) {
		try {
			this.unlock(code);
		} catch (CannotUnlockDoorException e) {
			return false;
		}
		this.close();
		return true;
	}

}
